package dissdraft01.display;

import dissdraft01.Grid;
import static dissdraft01.Game.GRID_HEIGHT;
import static dissdraft01.Game.GRID_LENGTH;
import java.awt.*;
import javax.swing.*;
 

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class DisplayOut
{
    JFrame frame;
    
    public DisplayOut(Grid grid)
    {
        frame = new JFrame();
        
        final int FRAME_WIDTH = 600;
        final int FRAME_HEIGHT = 800;
        frame.setSize(FRAME_HEIGHT, FRAME_WIDTH);
        frame.setTitle("Model Walker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.black);

        frame.add(new GrassComponent(grid));
    }
    /**
     * Takes the updated grid object and extracts the current location
     * of any Units and where there are no Units, take the current GrassPatch height.
     * Currently Displays out to the Console Output. May display poorly under
     * certain circumstances.
     * @param grid
     * @throws InterruptedException 
     */
    public void update() throws InterruptedException
    {
        frame.repaint();
        Thread.sleep(100);
    }
}
