package dissdraft01;

import dissdraft01.walkers.WalkerWeighted01;
import dissdraft01.walkers.WalkerDrift;
import dissdraft01.walkers.WalkerAngle;
import dissdraft01.walkers.WalkerWeighted02;
import dissdraft01.walkers.WalkerWeighted03;
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
    public static enum Walkers {DRIFT, WEIGHTED1, WEIGHTED2, WEIGHTED3, ANGLE};
    private int walkC = 0;

    /**
     * Initialises the grid, with GrassPatch array for each position on the grid
     * Then creates a pre-determined Unit, with current position and destination
     */
    public Grid()
    {
        dest = new GridReference[2];
        dest[0] = new GridReference(Game.GRID_WIDTH-(int)((double)Game.GRID_WIDTH*0.05),
                Game.GRID_HEIGHT-(int)((double)Game.GRID_HEIGHT*0.2));
        dest[1] = new GridReference(Game.GRID_WIDTH-(int)((double)Game.GRID_WIDTH*0.05),
                Game.GRID_HEIGHT-(int)((double)Game.GRID_HEIGHT*0.8));
        //dest[2] = new GridReference(5,50);
        defaultStart = new GridReference((int)((double)Game.GRID_WIDTH*0.05),
                Game.GRID_HEIGHT-(int)((double)Game.GRID_HEIGHT*0.5));
        defaultWalker = Walkers.DRIFT;
        
        createField();
        
        walkers = new ArrayList<>();
        //addUnits(randDest(), randDest());
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
            case WEIGHTED3:
                return walkers.add(new WalkerWeighted03(coord, dest, this));
            case ANGLE:
                return walkers.add(new WalkerAngle(coord, dest, this));
            default:
                return walkers.add(new WalkerDrift(coord, dest, this));
        }
    }
    public Boolean addUnits()
    {
        walkC++;
        if (walkC>(dest.length-1)) {walkC=0;}
        return addUnits(defaultStart, dest[walkC]);
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
        for (GrassPatch grassPatche : this.grassPatches)
        {
            if (grassPatche.equal(x, y))
            {
                return grassPatche;
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
        for (UnitInterface walker : this.walkers)
        {
            if (walker.equal(x, y))
            {
                return walker;
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
    @Override
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
                addUnits();
                break;
            case 2:
                grassPatches = new GrassPatch[(Game.GRID_WIDTH * Game.GRID_HEIGHT)];
                createField();
                break;
            case 3:
                walkers.removeAll(walkers);
                addUnits();
                break;
        }
    }
}
