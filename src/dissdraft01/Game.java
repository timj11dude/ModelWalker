package dissdraft01;

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
    public static final int GRID_HEIGHT = 20;
    public static final int GRID_LENGTH = 20;

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
        display = new DisplayOut();
        for (int i = 0; i < 40; i++)
        {
            System.out.println("Update cycle:" + i);
            update();
            display.update(grid);
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
            grid.grassPatches[i].Grow(growthAmount);
        }
        //Tell all Units to run a cylce of their move method's
        for (int x = 0; x < grid.units.size(); x++)
        {
            if (!grid.units.get(x).Move() == true)
            {
                grid.units.remove(x);
                grid.addUnits();
            }
            else
            {
                grid.getGrass(grid.units.get(x).getX(), grid.units.get(x).getY()).Trample(5);
            }
        }
        if (random.nextInt(10)== 0)
        {
            grid.addUnits();
        }
    }

}
