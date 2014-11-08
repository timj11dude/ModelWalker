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

    public GrassPatch(int x, int y)
    {
        super(x, y);
        maxHeight = defaultHeight;
        curHeight = defaultHeight;
        random = new Random();
    }
    
    public GrassPatch(int x, int y, int maxHeight)
    {
        super(x, y);
        this.maxHeight = maxHeight;
        random = new Random();
    }

    public GrassPatch(int x, int y, int maxHeight, int curHeight)
    {
        super(x, y);
        this.maxHeight = maxHeight;
        this.curHeight = curHeight;
        random = new Random();
    }

    public void Grow(int growth)
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

    public void Trample(int stomp)
    {
        this.curHeight -= stomp;
        if (curHeight < 0)
        {
            curHeight = 0;
        }
    }

    public String toString()
    {
        return getClass().getName() + "[maxHeight=" + getMaxHeight() + ", curHeight=" + getCurHeight() + "]";
    }

    public String format()
    {
        return String.format("%t/%t/", getMaxHeight(), getCurHeight());
    }

    public int getMaxHeight()
    {
        return this.maxHeight;
    }

    public int getCurHeight()
    {
        return this.curHeight;
    }

}
