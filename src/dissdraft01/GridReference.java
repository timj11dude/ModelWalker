package dissdraft01;

import static dissdraft01.Game.GRID_HEIGHT;
import static dissdraft01.Game.GRID_WIDTH;

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
    /**
     * Takes the integers provided and checks they lie within the grid.
     * @param x x-coordinate
     * @param y y-coordinate
     * @exception NullPointerException
     */
    public GridReference(int x, int y)
    {
        //Check that the attempted grid reference is within the defined grid size
        if (x > (GRID_WIDTH - 1) || y > (GRID_HEIGHT - 1) || x < 0 || y < 0)
        {
            throw new NullPointerException();
        }
        _x = x;
        _y = y;
    }
    public GridReference(GridReference x)
    {
        _x = x.getX();
        _y = x.getY();
    }
    public GridReference()
    {
        _x = 0;
        _y = 0;
    }
    
    /**
     * Provides a means of comparing the input coordinates,
 and checking if they are equal to the coordinates of the object.
     * @param x Integer
     * @param y Integer
     * @return Boolean
     */
    public Boolean equal(int x, int y)
    {
        return x == _x && y == _y;
    }
    /**
     * Provides a means of comparing the input coordinates,
 and checking if they are equal to the coordinates of the object.
     * @param coords GridReference
     * @return Boolean
     */
    public Boolean equals(GridReference coords)
    {
        return coords.getX() == _x && coords.getY() == _y;
    }
    @Override
    public boolean equals(Object other)
    {
        GridReference tester = (GridReference)other;
        //System.out.println("Testing: "+tester.toString());
        return tester.getX() == _x && tester.getY() == _y;
    }
    

    /**
     * Generates a string of the current coordinates of the object
     * @return String
     */
    public String gridCoord()
    {
        return this._x + "," + this._y;
    }

    /**
     * Returns the X coordinate of the object
     * @return Integer
     */
    public int getX()
    {
        return _x;
    }

    /**
     * Returns the Y coordinate of the object
     * @return Integer
     */
    public int getY()
    {
        return _y;
    }

    /**
     * Sets a new X coordinate
     * @param newX int
     */
    public void setX(int newX)
    {
        this._x = newX;
    }

    /**
     * Sets a new Y coordinate
     * @param newY  int
     */
    public void setY(int newY)
    {
        this._y = newY;
    }

    @Override
    public String toString()
    {
        return "[" + this._x + "," + this._y + "]";
    }
    public void set(GridReference newPos)
    {
        this._x = newPos.getX();
        this._y = newPos.getY();
    }
}
