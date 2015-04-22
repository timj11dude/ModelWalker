
package dissdraft01;

import dissdraft01.walkers.WalkerWeighted02;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Timothy Jacobson
 * @author eeue74
 */
public class InterfaceActionListener implements ActionListener, ChangeListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Reset Simulation".equals(e.getActionCommand())) {
            Game.instance.reset(1);
        }
        if ("Reset Grass".equals(e.getActionCommand())) {
            Game.instance.reset(2);
        }
        if ("Reset Walkers".equals(e.getActionCommand())) {
            Game.instance.reset(3);
        }
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if(!source.getValueIsAdjusting()) {
            double generalWeighted = (double)source.getValue() / (double)source.getMaximum();
            Game.weightWalker = generalWeighted;
        }
    }
}
