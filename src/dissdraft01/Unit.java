package dissdraft01;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class Unit extends GridReference
{
    protected GridReference dest;

    /**
     * Initialises a new Unit, with current position and destination
     * @param corX Integer
     * @param corY Integer
     * @param dest GridReference
     */
    public Unit(int corX, int corY, GridReference dest)
    {
        super(corX, corY);
        this.dest = dest;
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
    }

    /**
     * Attempts to move the Unit one cell towards its target.
     * Should it's current position, natch it's destination, then returns false.
     * @return Boolean
     */
    public Boolean Move()
    {
        //System.out.println("My current coords are:" + this);
        /*
         *Check if Unit is at it's destination
         */
        System.out.println(this.gridCoord());
        if (this.getX() == dest.getX() && this.getY() == dest.getY())
        {
            return false;
        } else
        {        
            /*
            *Check if the distance is shortest in which cell near the Unit
            */
            double shortest = Double.MAX_VALUE;
            GridReference pos = new GridReference(0,0);
            for (int x = -1; x < 2; x++)
            {
                for (int y = -1; y < 2; y++)
                {
                    double testLength = heuristic((this.getX() + x), (this.getY() + y));
                    if (testLength < shortest) {
                        shortest = testLength;
                        try
                        {
                        pos = new GridReference((this.getX() + x), (this.getY() + y));
                        }
                        catch (NullPointerException e) {
                            
                        }
                        
                    }
                }
            }
            this.setX(pos.getX());
            this.setY(pos.getY());
            return true;
        }
    }
    private double heuristic(int x, int y)
    {
        double D = 1.0;
        double dx = Math.abs(x - dest.getX());
        double dy = Math.abs(y - dest.getY());
        return D = D * (dx + dy);
    }
}
