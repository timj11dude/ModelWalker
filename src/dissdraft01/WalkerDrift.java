package dissdraft01;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.*;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class WalkerDrift extends Walker implements UnitInterface
{
    /**
     * Initialises a new Unit, with current position and destination
     * @param corX Integer
     * @param corY Integer
     * @param dest GridReference
     */
    public WalkerDrift(int corX, int corY, GridReference dest, Grid grid)
    {
        super(corX, corY, dest, grid);
        super.age=0;
    }
    /**
     * Initialises a new Unit, with current position and destination.
     * @param coords GridReference
     * @param dest GridReference
     */
    public WalkerDrift(GridReference coords, GridReference dest, Grid grid)
    {
        super(coords, dest, grid);
        age=0;
    }
    
    public Boolean move()
    {
        age++;
        //Create an array for each searched cell
        List<GridReference> posCoords = new ArrayList<GridReference>();
        posCoords = updateMap();
        if (this.getX() == dest.getX() && this.getY() == dest.getY() || posCoords.size() == 0)
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
    
    private List updateMap()
    {
        int s = 3;
        List<GridReference> posTar = new ArrayList<GridReference>();
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
                    if (angleg < (double)(Math.PI / 3))
                    {
                        //System.out.println("TRUE");
                        posTar.add(g);
                    }
                }
                catch (NullPointerException e)
                {
                    continue;
                }
            }
        }
        //System.out.println(posTar.size());
        return posTar;
    }
    
}
