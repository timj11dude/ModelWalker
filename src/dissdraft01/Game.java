package dissdraft01;

import dissdraft01.display.DisplayOut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class Game
{

    protected Grid grid;
    protected int growthAmount;
    protected DisplayOut display;
    public static int GRID_HEIGHT = 100;
    public static int GRID_WIDTH = 100;
    private int reset = 0;

    public static Game instance = null;
    
    /**
     * Initialises the grid object, the display object and sets the growth amount.
     * It then begins a set number update cycles print the current update cycle #.
     * Running the update() method for the grid as well as the display.
     * @throws InterruptedException 
     */
    public void run() throws InterruptedException
    {
        instance = this;
        grid = new Grid();
        growthAmount = 1;
        display = new DisplayOut(grid, this);
        int i = 0;
        loadProps();
        while (true)
        {
            System.out.println("Update cycle:" + i);
            System.out.println("# of Walkers:" + grid.walkers.size());
            update();
            display.update();
            System.out.println("---------------------------------");
            i++;
            if (reset != 0) {reset(reset);}
        }
    }

    /**
     * Informs all grassPatches to run their growth methods.
     * Informs all Units to attempt to move, if not, print a message.
     * Should a unit successfully move, apply the trample method to the grassPatch
     * at its current location.
     */
    public void update()
    {
        Random random = new Random();
        //Tell all grass patches to grow.
        for (int i = 0; i < grid.grassPatches.length; i++)
        {
            grid.grassPatches[i].grow(growthAmount);
        }
        try {
        Grid.watch.notifyAll();
        }
        catch (NullPointerException e) {
            
        }
                
        //Tell all Units to run a cylce of their move method's
        for (int x = 0; x < grid.walkers.size(); x++)
        {
            //grid.getGrass(grid.walkers.get(x).getX(), grid.walkers.get(x).getY()).trample(1);
            grid.spreadTrample(grid.walkers.get(x).getX(), grid.walkers.get(x).getY()); 
        }
        if (random.nextInt(40) == 0)
        {
            grid.addUnits();
        }
    }

    private void loadProps() {
        try {
            ModelProperties source = new ModelProperties();
            System.out.println();
            Properties props = source.getProperties();
            Game.GRID_HEIGHT = Integer.parseInt(props.getProperty("grid.size.height"));
            Game.GRID_WIDTH = Integer.parseInt(props.getProperty("grid.size.width"));
        }
        catch (IOException e) {
            System.out.println("Error loading properties: " + e);
        }
    }
    
    
    public void reset(int x) {
        this.grid.reset(x);
        this.reset = 0;
    } 
}
