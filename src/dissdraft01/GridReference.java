package dissdraft01;

/**
 * Humphrey Bogart 09/06/09
 * http://stackoverflow.com/questions/972893/java-best-type-to-store-grid-references
 *
 * Modified for use by: Timothy Jacobson eeue74
 */
public class GridReference
{

    private int _x;

    private int _y;

    public GridReference(int x, int y)
    {
        _x = x;
        _y = y;
    }
    
    public Boolean equal(int x, int y)
    {
        if (x == _x && y == _y)
        { 
            return true;
        }
        else 
        {
            return false;
        }
    }

    public String gridCoord()
    {
        return this._x + "," + this._y;
    }

    public int getX()
    {
        return _x;
    }

    public int getY()
    {
        return _y;
    }

    public void setX(int newX)
    {
        this._x = newX;
    }

    public void setY(int newY)
    {
        this._y = newY;
    }

    @Override
    public String toString()
    {
        return "[" + this._x + "," + this._y + "]";
    }
}
