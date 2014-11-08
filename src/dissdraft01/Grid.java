package dissdraft01;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class Grid
{

    protected int gridHeight;
    protected int gridLength;
    protected GrassPatch[] grassPatches;
    protected Unit[] units;

    public Grid(int height, int length)
    {
        this.gridHeight = height;
        this.gridLength = length;
        grassPatches = new GrassPatch[(gridLength * gridHeight)];
        int k = 0;
        for (int i = 0; i < gridHeight; i++)
        {
            for (int j = 0; j < gridLength; j++)
            {
                grassPatches[k] = new GrassPatch(i, j);
                k += 1;
            }
        }
        units = new Unit[1];
        for (int i = 0; i < units.length; i++)
        {
            units[i] = new Unit(new GridReference(5, 0), new GridReference(19, 19));
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
        return getClass().getName() + "[gridHeight=" + getGridHeight() + ", gridLength=" + getGridLength() + "]";
    }

    public String format()
    {
        return String.format("%/t%/t/", getGridHeight(), getGridLength());
    }

    public int getGridHeight()
    {
        return this.gridHeight;
    }

    public int getGridLength()
    {
        return this.gridLength;
    }
}
