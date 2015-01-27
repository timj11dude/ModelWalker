package dissdraft01;

import java.util.AbstractMap;
import java.util.HashMap;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class WalkerDrift extends GridReference implements UnitInterface
{
    protected GridReference dest;
    protected GridReference start;
    private double grad;
    private Grid grid;
    private int age;
    private double larLength = 0;
    private double smalLength = Double.MAX_VALUE;
    private double larGrad = 0; 
    private double smalGrad = Double.MAX_VALUE;
    private double larGrass = 0;
    private double smalGrass = Double.MAX_VALUE;

    /**
     * Initialises a new Unit, with current position and destination
     * @param corX Integer
     * @param corY Integer
     * @param dest GridReference
     */
    public WalkerDrift(int corX, int corY, GridReference dest, Grid grid)
    {
        super(corX, corY);
        this.start = new GridReference(corX, corY);
        setDest(dest);
        this.grid = grid;
        age=0;
    }
    /**
     * Initialises a new Unit, with current position and destination.
     * @param coords GridReference
     * @param dest GridReference
     */
    public WalkerDrift(GridReference coords, GridReference dest, Grid grid)
    {
        super(coords.getX(), coords.getY());
        this.dest = dest;
        this.start = coords;
        setDest(dest);
        this.grid = grid;
        age=0;
    }
    
    /**
     * Sets a new current position
     * @param curr GridReference
     */
    public void setCurr(GridReference curr)
    {
        setX(curr.getX());
        setY(curr.getY());
    }
    /**
     * Sets a new destination position
     * @param dest GridReference
     */
    public void setDest(GridReference dest)
    {
        this.dest = dest;
        //System.out.println("Start Gradient:"+(Math.abs(dest.getY() - this.getY())) / (double)(Math.abs(dest.getX() - this.getX()))+"| Start Coords:"+this.gridCoord()+"| Dest Coords:"+dest.gridCoord());
        this.grad = (Math.abs(dest.getY() - this.getY())) / (double)(Math.abs(dest.getX() - this.getX()));
    }
    
    public Boolean move()
    {
        age++;
        //Create an array for each searched cell
        HashMap tarCoords = new HashMap(9);
        updateMap(tarCoords);
        if (this.getX() == dest.getX() && this.getY() == dest.getY())
        {
            return false;
        } else
        {
            
        }
    }
    
    private HashMap updateMap(HashMap tarCoords)
    {
        int s = tarCoords.size();
        try
        {
            if (s % 2 != 0) {
                throw new Exception("Search size is invalid: " + s);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            
        }
        for (int i = 0; i < (s / 2); i++)
        {
            
        }
        
                
    }
    
    private double heuristicGrad(int x, int y)
    {
        double curGrad = (double)Math.abs(dest.getY() - y) / (double)Math.abs(dest.getX() - x);
        //System.out.println("CurGrad:"+curGrad+"| staGrad:"+this.grad);
        if (Double.isInfinite(Math.abs(curGrad - this.grad)))
        { 
            return 0;
        }
        return Math.abs(curGrad - this.grad);
    }
    private double heuristicDist(int x, int y)
    {
        return Math.sqrt(Math.pow((dest.getX() - (double)x), 2) + Math.pow((dest.getY() - (double)y), 2));
    }
    private double heuristicGrass(int x, int y)
    {
        if (this.equal(x, y)) { return Double.MAX_VALUE; }
        try
        {
            return grid.getGrass(x, y).getCurHeight();
        }
        catch (NullPointerException e)
        {
            return Double.MAX_VALUE;
        }
    }
    
    /**
     * Distance from current, to target
     * @param x Destination x coords.
     * @param y Destination y coords.
     * @return Distance value.
     */
    public double distance(int x, int y)
    {
        return Math.sqrt(Math.pow((double)x - getX(), 2) + Math.pow((double)y - getY(), 2));
    }
    /**
     * Distance from preset, to target.
     * @param x From x coords.
     * @param y From y coords.
     * @param dx Destination x coords.
     * @param dy Destination y coords.
     * @return Distance value.
     */
    public double distance(int x, int y, int dx, int dy)
    {
        return Math.sqrt(Math.pow((dx - (double)x), 2) + Math.pow(dy - (double)y, 2));
    }
    /**
     * Distance from current coords, to target
     * @param tar GridReference of target destination
     * @return Distance value.
     */
    public double distance(GridReference tar)
    {
        return Math.sqrt(Math.pow((double)tar.getX() - getX(), 2) + Math.pow((double)tar.getY() - getY(), 2));
    }
    /**
     * Distance from preset, to target.
     * @param sta Preset GridReference coords.
     * @param tar Destination GridReference coords.
     * @return Distance value.
     */
    public double distance(GridReference sta, GridReference tar)
    {
        return Math.sqrt(Math.pow((tar.getX() - (double)sta.getX()), 2) + Math.pow(tar.getY() - (double)sta.getY(), 2));
    }
    /**
     * http://stackoverflow.com/questions/1211212/how-to-calculate-an-angle-from-three-points
     * @param x
     * @param y
     * @return 
     */
    public double angle(int x, int y)
    {
        //arcos((P122 + P132 - P232) / (2 * P12 * P13))
        //p1 is origin
        double P23 = distance(x, y, dest.getX(), dest.getY());
        double P13 = distance(dest.getX(), dest.getY());
        double P12 = distance(x, y);
        return Math.acos((Math.pow(P12, 2) +
                Math.pow(P13, 2) - Math.pow(P23, 2)) / (2 * P12 * P13));
    }
    /**
     * http://stackoverflow.com/questions/1211212/how-to-calculate-an-angle-from-three-points
     * @param x
     * @param y
     * @param dx
     * @param dy
     * @return 
     */
    public double angle(int x, int y, int dx, int dy)
    {
        //arcos((P122 + P132 - P232) / (2 * P12 * P13))
        double P12 = distance(x, y, dx, dy);
        double P13 = distance(dx, dy);
        double P23 = distance(x, y);
        return Math.acos((Math.pow(P12, 2) +
                Math.pow(P13, 2) - Math.pow(P23, 2)) / (2 * P12 * P13));
    }
}
