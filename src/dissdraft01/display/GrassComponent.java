
package dissdraft01.display;

import static dissdraft01.Game.GRID_HEIGHT;
import static dissdraft01.Game.GRID_LENGTH;
import dissdraft01.Grid;
import dissdraft01.GrassPatch;
import dissdraft01.Walker;
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
            
            g2.setColor(new Color(0,(int)(((double)patch.getCurHeight() / (double)patch.getMaxHeight()) * 255),0));
            //System.out.println((int)((double)patch.getCurHeight() / (double)patch.getMaxHeight()) * 255);
            g2.fill(rect);
            //g2.draw(rect);
        }
        for (Walker walker: grid.getWalkers())
        {
            Rectangle unit = new Rectangle(walker.getX()*patchWidth, walker.getY()*patchHeight,patchWidth,patchHeight);
            g2.setColor(Color.red);
            g2.fill(unit);
        }
    }
}
