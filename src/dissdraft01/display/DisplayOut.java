package dissdraft01.display;

import dissdraft01.Game;
import dissdraft01.Grid;
import static dissdraft01.Game.GRID_HEIGHT;
import static dissdraft01.Game.GRID_WIDTH;
import dissdraft01.InterfaceActionListener;
import java.awt.*;
import javax.swing.*;
 

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class DisplayOut
{
    JFrame frameViewer;
    JFrame frameController;
    
    public DisplayOut(Grid grid, Game game)
    {
        frameViewer = new JFrame();
        frameController = new JFrame();
        
        final int FRAME_WIDTH = 800;
        final int FRAME_HEIGHT = 600;
        frameViewer.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frameViewer.setTitle("Model Walker Viewer");
        frameViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frameController.setSize(400, 100);
        frameController.setTitle("Model Walker Controller");
        frameController.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frameViewer.setVisible(true);
        frameViewer.getContentPane().setBackground(Color.black);
        
        frameController.setVisible(true);
        frameController.getContentPane().setBackground(Color.black);
        
        GrassComponent grassGrid = new GrassComponent(grid);
        
        JButton button1 = new JButton("Reset Simulation");
        JButton button2 = new JButton("Reset Walkers");
        JButton button3 = new JButton("Reset Grass");
        JSlider slider1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        
        button1.addActionListener(new InterfaceActionListener());
        button2.addActionListener(new InterfaceActionListener());
        button3.addActionListener(new InterfaceActionListener());
        slider1.addChangeListener(new InterfaceActionListener());
        
        slider1.setMajorTickSpacing(10);
        slider1.setMinorTickSpacing(5);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        
        frameController.add(button1, BorderLayout.CENTER);
        frameController.add(button2, BorderLayout.EAST);
        frameController.add(button3, BorderLayout.WEST);
        frameController.add(slider1, BorderLayout.NORTH);
        frameViewer.add(grassGrid);
        
        //grassGrid.
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
        frameViewer.repaint();
        Thread.sleep(10);
    }
}
