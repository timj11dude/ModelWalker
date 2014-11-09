package dissdraft01;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class Grid
{
    protected GrassPatch[] grassPatches;
    protected Unit[] units;

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
            units[i] = new Unit(new GridReference(5, 0), new GridReference(9, 8));
        }
        /*for (int i = 0; i < grassPatches.length; i++)
        {
            System.out.println(i + ":" + grassPatches[i]);
        }*/
    }

    public GrassPatch getGrass(int x, int y)
    {
        for (int i = 0; i < this.grassPatches.length; i++)
        {
            if (this.grassPatches[i].equal(x, y))
            {
                return this.grassPatches[i];
            }
        }
        return null;
    }

    public Unit getUnits(int i)
    {
        return units[i];
    }

    public String toString()
    {
        return getClass().getName() + "[Game.GRID_HEIGHT=" + Game.GRID_HEIGHT+ ", Game.GRID_LENGTH=" + Game.GRID_LENGTH+ "]";
    }

    public String format()
    {
        return String.format("%/t%/t/", Game.GRID_HEIGHT, Game.GRID_LENGTH);
    }
}
