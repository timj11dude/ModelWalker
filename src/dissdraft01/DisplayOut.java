package dissdraft01;


/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class DisplayOut
{
    public DisplayOut()
    {
    }
    /**
     * Takes the updated grid object and extracts the current location
     * of any Units and where there are no Units, take the current GrassPatch height.
     * Currently Displays out to the Console Output. May display poorly under
     * certain circumstances.
     * @param grid
     * @throws InterruptedException 
     */
    public void update(Grid grid) throws InterruptedException
    {
        for (int row = 0; row < Game.GRID_HEIGHT; row++)
        {
            for (int col = 0; col < Game.GRID_LENGTH; col++)
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
        Thread.sleep(100);
    }
}
