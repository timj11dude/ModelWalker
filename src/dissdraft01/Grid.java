package dissdraft01;

import static dissdraft01.Game.GRID_HEIGHT;
import static dissdraft01.Game.GRID_LENGTH;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class Grid
{
    protected GrassPatch[] grassPatches;
    protected Unit[] units;

    /**
     * Initialises the grid, with GrassPatch array for each position on the grid
     * Then creates a pre-determined Unit, with current position and destination
     */
    public Grid()
    {
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
        units = new Unit[1];
        for (int i = 0; i < units.length; i++)
        {
            units[i] = new Unit(new GridReference(5, 0), new GridReference(10, 19));
        }
        /*for (int i = 0; i < grassPatches.length; i++)
        {
            System.out.println(i + ":" + grassPatches[i]);
        }*/
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

    /**
     * For accessing Units based on their coordinates
     * @param x x-coordinate
     * @param y y-coordinate
     * @return Unit
     * @exception NullPointerException()
     */
    public Unit getUnits(int x, int y)
    {
        for (int i = 0; i < this.units.length; i++)
        {
            if (this.units[i].equal(x, y))
            {
                return this.units[i];
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
        return getClass().getName() + "[# of GrassPatches=" + grassPatches.length+ ", # of Units=" + units.length+ "]";
    }

    /**
     * Generates a list-able/comparable string of the current object.
     * @return string
     */
    public String format()
    {
        return String.format("%/t%/t/", grassPatches.length, units.length);
    }
}
