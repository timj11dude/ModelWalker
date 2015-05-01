package dissdraft01;

import dissdraft01.display.DisplayOut;
import java.io.IOException;
import java.util.Properties;

/**
 * Timothy Jacobson
 *
 * @author eeue74
 */
public class Game
{
    public static int GRID_HEIGHT = 100;
    public static int GRID_WIDTH = 100;
    public static Game instance = null;
    public static double weightWalker = 0;
    
    private static int maxCycle = 100;
    private static int spawnFrequency = 50;
    
    private int cycle;
    private int reset = 0;
    
    protected Grid grid;
    protected int growthAmount;
    protected DisplayOut display;
    
    /**
     * Initialises the grid object, the display object and sets the growth amount.
     * It then begins a set number update cycles print the current update cycle #.
     * Running the update() method for the grid as well as the display.
     * @throws InterruptedException 
     */
    public void run() throws InterruptedException {
        instance = this;
        
        //load grid based parameters
        load();
        
        display = new DisplayOut(grid);

        fileCSVOutput fileOut = new fileCSVOutput(grid);
        
        cycle = 0;
        sim:
        while (true)
        {
            System.out.println("     Update cycle:" + cycle);
            System.out.println("     # of Walkers:" + grid.walkers.size());
            System.out.println("Weight of Walkers:"+Game.weightWalker);
            update();
            display.update();
            System.out.println("---------------------------------");
            fileOut.save();
            cycle++;
            /*if (cycle>maxCycle) {
                reset(1);
                weightWalker += 0.1;
                cycle = 0;
            }
            if (weightWalker>1) {
                break;
            }*/
            if (reset != 0) {reset(reset);}
        }
    }

    private void load() {
        loadProps();
        System.out.println("Size of grid: "+GRID_HEIGHT+"x"+GRID_WIDTH);
        grid = new Grid();
        growthAmount = 1;
    }
    
    
    /**
     * Informs all grassPatches to run their growth methods.
     * Informs all Units to attempt to move, if not, print a message.
     * Should a unit successfully move, apply the trample method to the grassPatch
     * at its current location.
     */
    public void update() {
        //Tell all grass patches to grow.
        try {
            for (GrassPatch grassPatche : grid.grassPatches)
            {
                grassPatche.grow(growthAmount);
            }
        }
        catch (NullPointerException e) {
            System.err.print(e);
        }
        //Tell all Units to run a cylce of their move method's
        for (int x = 0; x < grid.walkers.size(); x++)
        {
            try {
                if (!grid.walkers.get(x).move())
                {
                    grid.walkers.remove(x);
                }
                else
                {
                    //grid.getGrass(grid.walkers.get(x).getX(), grid.walkers.get(x).getY()).trample(1);
                    grid.spreadTrample(grid.walkers.get(x).getX(), grid.walkers.get(x).getY()); 
                }
            }
            catch (Exception e) {
                System.err.print(e);
            }
        }
        if ((cycle%spawnFrequency)==0) {
            grid.addUnits();
        }
    }

    private void loadProps() {
        try {
            ModelProperties source = new ModelProperties();
            //System.out.println();
            Properties props = source.getProperties();
            Game.GRID_HEIGHT = Integer.parseInt(props.getProperty("grid.size.height"));
            Game.GRID_WIDTH = Integer.parseInt(props.getProperty("grid.size.width"));
            Game.maxCycle = Integer.parseInt(props.getProperty("game.cycle.maxCycle"));
            Game.spawnFrequency = Integer.parseInt(props.getProperty("game.cycle.frequency"));
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
