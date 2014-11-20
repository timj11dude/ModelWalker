
package dissdraft01.display;

import static dissdraft01.Game.GRID_HEIGHT;
import static dissdraft01.Game.GRID_LENGTH;
import dissdraft01.Grid;
import dissdraft01.GrassPatch;
import java.awt.*;
import javax.swing.*;

/**
 * Timothy Jacobson
 * @author eeue74
 */
public class GrassComponent extends JComponent
{
    int patchHeight;
    int patchWidth;
    Grid grid;
    
    public GrassComponent(Grid grid)
    {
        this.grid = grid;
    }
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        patchHeight = getHeight()/GRID_HEIGHT;
        patchWidth = getWidth()/GRID_LENGTH;
        for (GrassPatch patch: grid.getGrass())
        {
            Rectangle rect = new Rectangle(patch.getX()*patchWidth, patch.getY()*patchHeight, patchWidth, patchHeight);
            if (patch.getCurHeight() > patch.getMaxHeight()/2)
            {
                g2.setColor(Color.GREEN);
            } else 
            {
                g2.setColor(Color.CYAN);
            }
            g2.fill(rect);
            g2.draw(rect);
        }
    }
}
