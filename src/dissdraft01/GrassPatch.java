package dissdraft01;

import java.util.Random;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class GrassPatch extends GridReference
{

    protected int maxHeight;
    protected int curHeight;
    private int defaultHeight = 9;
    private Random random;

    /**
     * Initialises a new GrassPatch with it's own coordinates.
     * Should no height parameters be provided, the internal default is used.
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public GrassPatch(int x, int y)
    {
        super(x, y);
        maxHeight = defaultHeight;
        curHeight = defaultHeight;
        random = new Random();
    }
    /**
     * Initialises a new GrassPatch with it's own coordinates.
     * Will set both the current and max grass height.
     * @param x x-coordinate
     * @param y y-coordinate
     * @param maxHeight Current and Max height the grass can reach
     */
    public GrassPatch(int x, int y, int maxHeight)
    {
        super(x, y);
        this.maxHeight = maxHeight;
        this.curHeight = maxHeight;
        random = new Random();
    }
    /**
     * Initialises a new GrassPatch with it's own coordinates.
     * Will allow both the current and max grass height to be set.
     * @param x x-coordinate
     * @param y y-coordinate
     * @param maxHeight Max height the grass can reach
     * @param curHeight Current height of the grass.
     */
    public GrassPatch(int x, int y, int maxHeight, int curHeight)
    {
        super(x, y);
        this.maxHeight = maxHeight;
        this.curHeight = curHeight;
        random = new Random();
    }

    /**
     * Increases the height of the grass, but not beyond it's maximum specified.
     * Hard coded 50% chance the grass will grow or not.
     * @param growth Amount grass should grow at
     */
    public void grow(int growth)
    {
        if (random.nextBoolean())
        {
        this.curHeight += growth;
        }
        if (curHeight > maxHeight)
        {
            curHeight = maxHeight;
        }
    }

    /**
     * Decreases the height of the grass by the input amount
     * @param stomp Integer
     */
    public void trample(int stomp)
    {
        this.curHeight -= stomp;
        if (curHeight < 0)
        {
            curHeight = 0;
        }
    }

    @Override
    public String toString()
    {
        return getClass().getName() + "[maxHeight=" + getMaxHeight() + ", curHeight=" + getCurHeight() + "]";
    }

    /**
     * Generates a list-able/comparable string of the current object.
     * @return string
     */
    public String format()
    {
        return String.format("%t/%t/", getMaxHeight(), getCurHeight());
    }

    /**
     * Return the maximum achievable grass height
     * @return Integer
     */
    public int getMaxHeight()
    {
        return this.maxHeight;
    }

    /**
     * Return the current grass height
     * @return Integer
     */
    public int getCurHeight()
    {
        return this.curHeight;
    }

}
