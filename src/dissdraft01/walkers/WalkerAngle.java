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
    
    double shortest;
    GridReference pos;
    boolean posChange;
    
    @Override
    void preMove() {
        shortest = Double.MAX_VALUE;
        pos = new GridReference();
        posChange = false;
    }
    
    @Override
    void stepMove(int x, int y) {
        
        double angleResult = angle((x), (y));
        
        if (angleResult > 90) { return; }
        
        if (angleResult < shortest)
        {
            shortest = angle((x), (y));
            try
            {
            pos = new GridReference((x), (y));
            weight = angleResult;
            posChange=true;
            }
            catch (NullPointerException e)
            {

            }
        }
    }
    
    @Override
    boolean postMove() {
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
