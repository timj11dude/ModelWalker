package dissdraft01;

import dissdraft01.display.DisplayOut;
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
    public static final int GRID_HEIGHT = 100;
    public static final int GRID_LENGTH = 100;

    /**
     * Initialises the grid object, the display object and sets the growth amount.
     * It then begins a set number update cycles print the current update cycle #.
     * Running the update() method for the grid as well as the display.
     * @throws InterruptedException 
     */
    public void run() throws InterruptedException
    {
        grid = new Grid();
        growthAmount = 1;
        display = new DisplayOut(grid);
        for (int i = 0; i < 1000; i++)
        {
            System.out.println("Update cycle:" + i);
            update();
            display.update();
            System.out.println("---------------------------------");
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
        //Tell all Units to run a cylce of their move method's
        for (int x = 0; x < grid.walkers.size(); x++)
        {
            if (!grid.walkers.get(x).Move() == true)
            {
                grid.walkers.remove(x);
                //grid.addUnits();
            }
            else
            {
                //grid.getGrass(grid.walkers.get(x).getX(), grid.walkers.get(x).getY()).trample(1);
                grid.spreadTrample(grid.walkers.get(x).getX(), grid.walkers.get(x).getY());
            }
        }
        if (random.nextInt(4)== 0)
        {
            grid.addUnits();
        }
    }

}
