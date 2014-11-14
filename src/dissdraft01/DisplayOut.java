package dissdraft01;

import static dissdraft01.Game.GRID_HEIGHT;
import static dissdraft01.Game.GRID_LENGTH;


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
        for (int row = 0; row < GRID_HEIGHT; row++)
        {
            for (int col = 0; col < GRID_LENGTH; col++)
            {
                boolean check = false;
                for (int i = 0; i < grid.units.size(); i++)
                {
                    if ((grid.units.get(i).gridCoord().equals(col + "," + row)) && (check == false))
                    {
                        System.out.print("X");
                        check = true;
                    }
                }
                if (check == false)
                {
                    System.out.print(grid.getGrass(col, row).curHeight);
                }
                
            }
            System.out.println();
        }
        Thread.sleep(1000);
    }
}
