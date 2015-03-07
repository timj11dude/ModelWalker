package dissdraft01;

import dissdraft01.walkers.WalkerWeighted01;
import dissdraft01.walkers.WalkerDrift;
import dissdraft01.walkers.WalkerAngle;
import dissdraft01.walkers.WalkerWeighted02;
import static dissdraft01.Game.GRID_HEIGHT;
import static dissdraft01.Game.GRID_WIDTH;
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
    protected List<UnitInterface> walkers;
    protected GridReference[] dest;
    private GridReference defaultStart;
    private Walkers defaultWalker;
    public static enum Walkers {DRIFT, WEIGHTED1, WEIGHTED2, ANGLE};

    /**
     * Initialises the grid, with GrassPatch array for each position on the grid
     * Then creates a pre-determined Unit, with current position and destination
     */
    public Grid()
    {
        dest = new GridReference[4];
        dest[0] = new GridReference(98,80);
        dest[1] = new GridReference(98,20);
        dest[2] = new GridReference(10,98);
        dest[3] = new GridReference(10,10);
        defaultStart = new GridReference(2, 2);
        defaultWalker = Walkers.WEIGHTED2;
        
        createField();
        
        walkers = new ArrayList<UnitInterface>();
        addUnits(randDest(), randDest());
        /*for (int i = 0; i < grassPatches.length; i++)
        {
            System.out.println(i + ":" + grassPatches[i]);
        }*/
    }
    
    private void createField() {
        grassPatches = new GrassPatch[(Game.GRID_WIDTH * Game.GRID_HEIGHT)];
        int k = 0;
        for (int i = 0; i < Game.GRID_HEIGHT; i++)
        {
            for (int j = 0; j < Game.GRID_WIDTH; j++)
            {
                grassPatches[k] = new GrassPatch(i, j);
                k += 1;
            }
        }
    }
    public Boolean addUnits(GridReference coord, GridReference dest)
    {
        switch (defaultWalker) {
            case DRIFT:
                return walkers.add(new WalkerDrift(coord, dest, this));
            case WEIGHTED1:
                return walkers.add(new WalkerWeighted01(coord, dest, this));
            case WEIGHTED2:
                return walkers.add(new WalkerWeighted02(coord, dest, this));
            case ANGLE:
                return walkers.add(new WalkerAngle(coord, dest, this));
            default:
                return walkers.add(new WalkerDrift(coord, dest, this));
        }
    }
    public Boolean addUnits()
    {
        switch (defaultWalker) {
            case DRIFT:
                return walkers.add(new WalkerDrift(randDest(), randDest(), this));
            case WEIGHTED1:
                return walkers.add(new WalkerWeighted01(randDest(), randDest(), this));
            case WEIGHTED2:
                return walkers.add(new WalkerWeighted02(randDest(), randDest(), this));
            case ANGLE:
                return walkers.add(new WalkerAngle(randDest(), randDest(), this));
            default:
                return walkers.add(new WalkerDrift(randDest(), randDest(), this));
        }
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
    public UnitInterface getWalkers(int x, int y)
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
    public List<UnitInterface> getWalkers()
    {
        return this.walkers;
    }

    public GridReference[] getDestinations()
    {
        return this.dest;
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
    
    public void reset(int x) {
        switch (x) {
            case 1:
                grassPatches = new GrassPatch[(Game.GRID_WIDTH * Game.GRID_HEIGHT)];
                walkers.removeAll(walkers);
                createField();
                addUnits(randDest(), randDest());
                break;
            case 2:
                grassPatches = new GrassPatch[(Game.GRID_WIDTH * Game.GRID_HEIGHT)];
                createField();
                break;
            case 3:
                walkers.removeAll(walkers);
                addUnits(randDest(), randDest());
                break;
        }
    }
}
