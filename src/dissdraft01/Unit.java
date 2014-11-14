package dissdraft01;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class Unit extends GridReference
{
    protected GridReference dest;
    protected GridReference start;
    private double grad;
    private int grassHeight;

    /**
     * Initialises a new Unit, with current position and destination
     * @param corX Integer
     * @param corY Integer
     * @param dest GridReference
     */
    public Unit(int corX, int corY, GridReference dest)
    {
        super(corX, corY);
        this.start = new GridReference(corX, corY);
        setDest(dest);
    }
    /**
     * Initialises a new Unit, with current position and destination.
     * @param coords GridReference
     * @param dest GridReference
     */
    public Unit(GridReference coords, GridReference dest)
    {
        super(coords.getX(), coords.getY());
        this.dest = dest;
        this.start = coords;
        setDest(dest);
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
    public Boolean Move(int newGrass)
    {
        this.grassHeight = newGrass;
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
            /*
            *Check if the distance is shortest in which cell near the Unit
            */
            double shortest = Double.MAX_VALUE;
            double larLength = 0; double smalLength = Double.MAX_VALUE;
            double larGrad = 0; double smalGrad = Double.MAX_VALUE;
            double larGrass = 0; double smalGrass = Double.MAX_VALUE;
            GridReference pos = new GridReference(0,0);
            for (int x = -1; x < 2; x++)
            {
                for (int y = -1; y < 2; y++)
                {
                    if (this.getY() + y == start.getY() && this.getX() + x == start.getX() || (y==0 && x==0)) { continue; }
                    double testLength = heuristicDist((this.getX() + x), (this.getY() + y));
                    double testGrad = heuristicGrad((this.getX() + x), (this.getY() + y));
                    double testGras = heuristicGrass();
                    double testWeight = testLength * 0.2 + testGrad * 0.3 + testGras * 0.5;
                    if (this.getY() + y == dest.getY() && this.getX() + x == dest.getX() ) {testWeight = 0.0; }
                    //System.out.println("X:"+x+"Y:"+y+"| Dist:"+testLength+"| Grad:"+testGrad+"| Weighted:"+testWeight);
                    if (testWeight < shortest) {
                        shortest = testWeight;
                        try
                        {
                        pos = new GridReference((this.getX() + x), (this.getY() + y));
                        }
                        catch (NullPointerException e) {
                            
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
    private double heuristicGrass()
    {
        return (double)this.grassHeight;
    }
}
