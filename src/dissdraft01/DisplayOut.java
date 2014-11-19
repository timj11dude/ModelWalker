package dissdraft01;

import static dissdraft01.Game.GRID_HEIGHT;
import static dissdraft01.Game.GRID_LENGTH;
import javax.swing.*;


/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class DisplayOut
{
    public DisplayOut()
    {
        JFrame frame = new JFrame();
        
        final int FRAME_WIDTH = 600;
        final int FRAME_HEIGHT = 800;
        frame.setSize(FRAME_HEIGHT, FRAME_WIDTH);
        frame.setTitle("Model Walker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
                for (int i = 0; i < grid.walkers.size(); i++)
                {
                    if ((grid.walkers.get(i).gridCoord().equals(col + "," + row)) && (check == false))
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
        Thread.sleep(100);
    }
}
