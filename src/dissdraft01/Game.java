package dissdraft01;

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

    public void run() throws InterruptedException
    {
        grid = new Grid(9, 9);
        growthAmount = 1;
        display = new DisplayOut(grid.getGridLength(), grid.getGridHeight());
        for (int i = 0; i < 40; i++)
        {
            System.out.println("Update cycle:" + i);
            update();
            display.update(grid);
        }
    }

    public void update()
    {
        //Tell all grass patches to grow.
        for (int i = 0; i < grid.grassPatches.length; i++)
        {
            grid.grassPatches[i].Grow(growthAmount);
        }
        //Tell all Units to run a cylce of their move method's
        for (int x = 0; x < grid.units.length; x++)
        {
            if (!grid.units[x].Move() == true)
            {
                System.out.println("Unit finished!");
            }
            else
            {
                grid.getGrass(grid.units[x].getX(), grid.units[x].getY()).Trample(5);
            }
        }
        
    }

}
