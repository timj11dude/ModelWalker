package dissdraft01;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class Walker extends GridReference
{
    protected GridReference dest;
    protected GridReference start;
    private double grad;
    private Grid grid;
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
    public Walker(int corX, int corY, GridReference dest, Grid grid)
    {
        super(corX, corY);
        this.start = new GridReference(corX, corY);
        setDest(dest);
        this.grid = grid;
    }
    /**
     * Initialises a new Unit, with current position and destination.
     * @param coords GridReference
     * @param dest GridReference
     */
    public Walker(GridReference coords, GridReference dest, Grid grid)
    {
        super(coords.getX(), coords.getY());
        this.dest = dest;
        this.start = coords;
        setDest(dest);
        this.grid = grid;
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

    /**
     * Attempts to move the Unit one cell towards its target.
     * Should it's current position, natch it's destination, then returns false.
     * @return Boolean
     */
    public Boolean move()
    {
        //System.out.println("My current coords are:" + this.gridCoord());
        /*
         *Check if Unit is at it's destination
         */
        //System.out.println(this.gridCoord());
        if (this.getX() == dest.getX() && this.getY() == dest.getY())
        {
            return false;
        } else
        {        
            double shortest = Double.MAX_VALUE;
            updateScale();
            GridReference pos = new GridReference(0,0);
            for (int x = -1; x < 2; x++)
            {
                for (int y = -1; y < 2; y++)
                {
                    if (this.getY() + y == start.getY() && this.getX() + x == start.getX() || (y==0 && x==0)) { continue; }
                    double testLength = checkScale(0 ,heuristicDist((this.getX() + x), (this.getY() + y)));
                    double testGrad = checkScale(1 ,heuristicGrad((this.getX() + x), (this.getY() + y)));
                    double testGras = checkScale(2 ,heuristicGrass((this.getX() + x), (this.getY() + y)));
                    double testWeight = testLength * 0.0001 + testGrad * 0.9 + testGras * 0.00005;
                    
                    if (this.getY() + y == dest.getY() && this.getX() + x == dest.getX() ) {testWeight = 0.0; }
                    System.out.println("X:"+x+"Y:"+y+"| Dist:"+testLength+"| Grad:"+testGrad+"| Grass:"+testGras+"| Weighted:"+testWeight);
                    if (testWeight < shortest) {
                        shortest = testWeight;
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
            //System.out.println("NewPosition:"+pos.gridCoord());
            this.setX(pos.getX());
            this.setY(pos.getY());
            return true;
        }
    }
    
    public Boolean moveD()
    {
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
                    //System.out.println("X:"+x+"Y:"+y+"| Weighted:" + weighted((this.getX() + x), (this.getY() + y)));
                    if (weighted((this.getX() + x), (this.getY() + y)) < shortest)
                    {
                        shortest = weighted((this.getX() + x), (this.getY() + y));
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
    
    private double weighted(int x, int y)
    {
        double weight = distance(x, y, dest.getX(), dest.getY()) / distance(start, dest);
        //System.out.println("X:"+ x + "Y:" + y + "Weight:" + weight);
        return distance(x, y, dest.getX(), dest.getY()) + weight * heuristicGrass(x, y);
    }
    private double heuristicGrad(int x, int y)
    {
        double curGrad = (double)Math.abs(dest.getY() - y) / (double)Math.abs(dest.getX() - x);
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
    private void updateScale()
    {
        for (int x = -1; x < 2; x++)
        {
            for (int y = -1; y < 2; y++)
            {
                if (smalLength > heuristicDist((this.getX() + x), (this.getY() + y)))
                {
                    smalLength = heuristicDist((this.getX() + x), (this.getY() + y));
                }
                if (larLength < heuristicDist((this.getX() + x), (this.getY() + y)))
                {
                    larLength = heuristicDist((this.getX() + x), (this.getY() + y));
                }
                if (smalGrad > heuristicGrad((this.getX() + x), (this.getY() + y)))
                {
                    smalGrad = heuristicGrad((this.getX() + x), (this.getY() + y));
                }
                if (larGrad < heuristicGrad((this.getX() + x), (this.getY() + y)))
                {
                    larGrad = heuristicGrad((this.getX() + x), (this.getY() + y));
                }
                if (smalGrass > heuristicGrass((this.getX() + x), (this.getY() + y)))
                {
                    smalGrass = heuristicGrass((this.getX() + x), (this.getY() + y));
                }
                if (larGrass < heuristicGrass((this.getX() + x), (this.getY() + y)))
                {
                    larGrass = heuristicGrass((this.getX() + x), (this.getY() + y));
                }
            }
        }
    }
    /**
     * @return Scale value out of the degree between best and worst option of it's type.
     */
    private double checkScale(int type, double value)
    {
        switch (type){
            case 0: return ((value - smalLength) / (larLength - smalLength)) * 100;
            case 1: return ((value - smalGrad) / (larGrad - smalGrad)) * 100;
            case 2: return ((value - smalGrass) / (larGrass - smalGrass)) * 100;
        }
        return 100;
    }
    /**
     * Distance from current, to target
     * @param x Destination x coords.
     * @param y Destination y coords.
     * @return Distance value.
     */
    private double distance(int x, int y)
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
    private double distance(int x, int y, int dx, int dy)
    {
        return Math.sqrt(Math.pow((dx - (double)x), 2) + Math.pow(dy - (double)y, 2));
    }
    /**
     * Distance from current coords, to target
     * @param tar GridReference of target destination
     * @return Distance value.
     */
    private double distance(GridReference tar)
    {
        return Math.sqrt(Math.pow((double)tar.getX() - getX(), 2) + Math.pow((double)tar.getY() - getY(), 2));
    }
    /**
     * Distance from preset, to target.
     * @param sta Preset GridReference coords.
     * @param tar Destination GridReference coords.
     * @return Distance value.
     */
    private double distance(GridReference sta, GridReference tar)
    {
        return Math.sqrt(Math.pow((tar.getX() - (double)sta.getX()), 2) + Math.pow(tar.getY() - (double)sta.getY(), 2));
    }
}
