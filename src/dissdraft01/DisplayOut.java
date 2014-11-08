package dissdraft01;


/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class DisplayOut
{
    //Point2D newPoint;
    int width;
    int height;
    public DisplayOut(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    public void update(Grid grid) throws InterruptedException
    {
        for (int row = 0; row < height; row++)
        {
            for (int col = 0; col < width; col++)
            {
                boolean check = false;
                for (int i = 0; i < grid.units.length; i++)
                {
                    if ((grid.units[i].gridCoord().equals(row + "," + col)) && (check == false))
                    {
                        System.out.print("X");
                        check = true;
                    }
                }
                if (check == false)
                {
                    System.out.print(grid.getGrass(row, col).curHeight);
                }
                
            }
            System.out.println();
        }
        Thread.sleep(1000);
    }
}
