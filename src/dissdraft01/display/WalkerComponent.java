
package dissdraft01.display;

import static dissdraft01.Game.GRID_HEIGHT;
import static dissdraft01.Game.GRID_LENGTH;
import dissdraft01.Grid;
import dissdraft01.Walker;
import java.awt.*;
import javax.swing.*;

/**
 * Timothy Jacobson
 * @author eeue74
 */
public class WalkerComponent extends JComponent
{

    Grid grid;
    
    public WalkerComponent(Grid grid)
    {
        this.grid = grid;
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        int patchHeight = getHeight()/GRID_HEIGHT;
        int patchWidth = getWidth()/GRID_LENGTH;
        for (Walker walker: grid.getWalkers())
        {
            Rectangle unit = new Rectangle(walker.getX()*patchWidth, walker.getY()*patchHeight,patchWidth,patchHeight);
            g2.setColor(Color.red);
            g2.fill(unit);
        }
    }
}
