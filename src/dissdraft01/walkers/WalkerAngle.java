package dissdraft01.walkers;

import dissdraft01.Grid;
import dissdraft01.GridReference;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class WalkerAngle extends Walker
{
    /**
     * Initialises a new Unit, with current position and destination
     * @param corX Integer
     * @param corY Integer
     * @param dest GridReference
     * @param grid
     */
    public WalkerAngle(int corX, int corY, GridReference dest, Grid grid)
    {
        super(corX, corY, dest, grid);
    }
    /**
     * Initialises a new Unit, with current position and destination.
     * @param coords GridReference
     * @param dest GridReference
     * @param grid
     */
    public WalkerAngle(GridReference coords, GridReference dest, Grid grid)
    {
        super(coords, dest, grid);
    }
    
    @Override
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
            boolean posChange = false;
            for (int x = -1; x < 2; x++)
            {
                for (int y = -1; y < 2; y++)
                {
                    if (this.getY() + y == start.getY() && this.getX() + x == start.getX() || (y==0 && x==0)) { continue; }
                    double angleResult = angle((this.getX() + x), (this.getY() + y));
                    if (angleResult > 90) { continue; }
                    System.out.println("X:"+x+"Y:"+y+"| Weighted:" + angleResult);
                    if (angleResult < shortest)
                    {
                        shortest = angle((this.getX() + x), (this.getY() + y));
                        try
                        {
                        pos = new GridReference((this.getX() + x), (this.getY() + y));
                        posChange=true;
                        }
                        catch (NullPointerException e)
                        {
                            
                        }
                    }
                }
            }
            if (posChange){
                this.setX(pos.getX());
                this.setY(pos.getY());
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
