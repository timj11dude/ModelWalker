package dissdraft01;

import static dissdraft01.Game.GRID_HEIGHT;
import static dissdraft01.Game.GRID_LENGTH;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Timothy Jacobson
 *http://stackoverflow.com/questions/2581972/how-can-i-make-a-resizable-array-in-java
 * @author eeue74
 */
public class Grid
{
    protected GrassPatch[] grassPatches;
    protected List<Walker> walkers;
    protected GridReference[] dest;
    private GridReference defaultStart;

    /**
     * Initialises the grid, with GrassPatch array for each position on the grid
     * Then creates a pre-determined Unit, with current position and destination
     */
    public Grid()
    {
        dest = new GridReference[2];
        dest[0] = new GridReference(15,18);
        dest[1] = new GridReference(5,18);
        defaultStart = new GridReference(10, 0);
        
        grassPatches = new GrassPatch[(Game.GRID_LENGTH * Game.GRID_HEIGHT)];
        int k = 0;
        for (int i = 0; i < Game.GRID_HEIGHT; i++)
        {
            for (int j = 0; j < Game.GRID_LENGTH; j++)
            {
                grassPatches[k] = new GrassPatch(i, j);
                k += 1;
            }
        }
        walkers = new ArrayList<Walker>();
        addUnits(defaultStart, randDest());
        /*for (int i = 0; i < grassPatches.length; i++)
        {
            System.out.println(i + ":" + grassPatches[i]);
        }*/
    }

    public Boolean addUnits(GridReference coord, GridReference dest)
    {
        return walkers.add(new Walker(coord, dest, this));
    }
    public Boolean addUnits()
    {
        return walkers.add(new Walker(defaultStart, randDest(), this));
    }
    private GridReference randDest()
    {
        Random random = new Random();
        return dest[random.nextInt(dest.length)];
    }
    
    
    public void spreadTrample(int nx, int ny)
    {
        getGrass(nx,ny).trample(1);
        for (int x = -1; x < 2; x++)
        {
            for (int y = -1; y < 2; y++)
            {
                try
                {
                    getGrass(getGrass(nx,ny).getX()+x,getGrass(nx,ny).getY()+y).trample(1);
                }
                catch (NullPointerException e)
                {
                }
            }
        }
    }
    /**
     * For accessing grassPatches based on their coordinates.
     * @param x x-coordinate
     * @param y y-coordinate
     * @exception NullPointerException()
     * @return GrassPatch
     */
    public GrassPatch getGrass(int x, int y)
    {
        for (int i = 0; i < this.grassPatches.length; i++)
        {
            if (this.grassPatches[i].equal(x, y))
            {
                return this.grassPatches[i];
            }
        }
        throw new NullPointerException();
    }
    public GrassPatch[] getGrass()
    {
        return grassPatches;
    }

    /**
     * For accessing Units based on their coordinates
     * @param x x-coordinate
     * @param y y-coordinate
     * @return Unit
     * @exception NullPointerException()
     */
    public Walker getUnits(int x, int y)
    {
        for (int i = 0; i < this.walkers.size(); i++)
        {
            if (this.walkers.get(i).equal(x, y))
            {
                return this.walkers.get(i);
            }
        }
        throw new NullPointerException();
    }

    /**
     * Generates a string describing the current object and the number of
     * GrassPatches and Units are contained within.
     * @return String
     */
    public String toString()
    {
        return getClass().getName() + "[# of GrassPatches=" + grassPatches.length+ ", # of Units=" + walkers.size()+ "]";
    }

    /**
     * Generates a list-able/comparable string of the current object.
     * @return string
     */
    public String format()
    {
        return String.format("%/t%/t/", grassPatches.length, walkers.size());
    }
}
