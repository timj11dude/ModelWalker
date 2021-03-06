package dissdraft01.walkers;

import dissdraft01.Game;
import dissdraft01.GrassPatch;
import dissdraft01.Grid;
import dissdraft01.GridReference;
import java.util.*;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class WalkerDrift extends Walker
{
    /**
     * Initialises a new Unit, with current position and destination
     * @param corX Integer
     * @param corY Integer
     * @param dest GridReference
     * @param grid
     */
    public WalkerDrift(int corX, int corY, GridReference dest, Grid grid)
    {
        super(corX, corY, dest, grid);
    }
    /**
     * Initialises a new Unit, with current position and destination.
     * @param coords GridReference
     * @param dest GridReference
     * @param grid
     */
    public WalkerDrift(GridReference coords, GridReference dest, Grid grid)
    {
        super(coords, dest, grid);
    }
    
    @Override
    public boolean move()
    {
        age++;
        //Create an array for each searched cell
        List<GridReference> posCoords;
        posCoords = updateMap();
        if (this.getX() == dest.getX() && this.getY() == dest.getY() || posCoords.isEmpty())
        {
            return false;
        } else
        {
            Random r = new Random();
            int z = r.nextInt(posCoords.size());
            this.set(posCoords.get(z));
            return true;
        }
    }
    
    void preMove(){}
    void stepMove(int x, int y){}
    boolean postMove(){return false;}
    
    private List updateMap()
    {
        int s = 3;
        List<GridReference> posTar = new ArrayList<>();
        GrassPatch g;
        for (int x = 0; x < s; x++)
        {
            for (int y = 0; y < s; y++)
            {
                try
                {
                    g = grid.getGrass(this.getX()+(x-1), this.getY()+(y-1));
                    double angleg = angle(g.getX(), g.getY());
                    //System.out.println("x:"+g.getX()+"y:"+g.getY()+"="+angleg + "<" + Math.PI / 2);
                    if (angleg < (Math.PI * Game.weightWalker))
                    {
                        //System.out.println("TRUE");
                        posTar.add(g);
                    }
                }
                catch (NullPointerException e)
                {
                }
            }
        }
        //System.out.println(posTar.size());
        return posTar;
    }
    
}
