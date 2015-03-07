package dissdraft01.walkers;

import dissdraft01.Grid;
import dissdraft01.GridReference;
import dissdraft01.UnitInterface;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class WalkerAngle extends Walker implements UnitInterface
{
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
    public WalkerAngle(int corX, int corY, GridReference dest, Grid grid)
    {
        super(corX, corY, dest, grid);
        age=0;
    }
    /**
     * Initialises a new Unit, with current position and destination.
     * @param coords GridReference
     * @param dest GridReference
     */
    public WalkerAngle(GridReference coords, GridReference dest, Grid grid)
    {
        super(coords, dest, grid);
        age=0;
    }
    
    public Boolean move()
    {
        age++;
        if (this.getX() == dest.getX() && this.getY() == dest.getY())
        {
            return false;
        } else
        {
            double shortest = Double.MAX_VALUE;
            GridReference pos = new GridReference();
            for (int x = -1; x < 2; x++)
            {
                for (int y = -1; y < 2; y++)
                {
                    if (this.getY() + y == start.getY() && this.getX() + x == start.getX() || (y==0 && x==0)) { continue; }
                    double weightedResult = weighted((this.getX() + x), (this.getY() + y));
                    double angleResult = angle((this.getX() + x), (this.getY() + y));
                    if (angleResult > 90) { continue; }
                    System.out.println("X:"+x+"Y:"+y+"| Weighted:" + weightedResult);
                    if (angleResult < shortest)
                    {
                        shortest = angle((this.getX() + x), (this.getY() + y));
                        try
                        {
                        pos = new GridReference((this.getX() + x), (this.getY() + y));
                        }
                        catch (NullPointerException e)
                        {
                            
                        }
                    }
                }
            }
            this.setX(pos.getX());
            this.setY(pos.getY());
            return true;
        }
    }
    
    public double weighted(int x, int y)
    {
        //Weighting based on the proximity of the destination since the start.
        //Gets smaller the closer to the destination.
        double weightD = distance(x, y, dest.getX(), dest.getY()) / distance(start, dest);
        double weightT = Math.max((100 - Math.pow((age / 20), 2))/100, 0);
        //Decrease the weight based on the age of the walker.
        double weight = Math.min(weightD, weightT);
        weight = 0;
        //System.out.println("X:"+ x + "Y:" + y + "Weight:" + weight);
        double distFromCur2Dest = distance(dest.getX(), dest.getY());
        double distFromTar2Dest = distance(x, y, dest.getX(), dest.getY());
        double dist2Tar = distance(x, y);
        dist2Tar = 0;
        
        return (distFromCur2Dest - distFromTar2Dest - dist2Tar) + weight * heuristicGrass(x, y);
   
    }
    private double heuristicGrad(int x, int y)
    {
        double curGrad = Math.abs(dest.getY() - y) / Math.abs(dest.getX() - x);
        //System.out.println("CurGrad:"+curGrad+"| staGrad:"+this.grad);
        if (Double.isInfinite(Math.abs(curGrad - this.grad)))
        { return 0;
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
}
