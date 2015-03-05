
package dissdraft01;

import Walkers.WalkerWeighted02;
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
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Reset Simulation") {
            Game.instance.reset(1);
        }
        if (e.getActionCommand() == "Reset Grass") {
            Game.instance.reset(2);
        }
        if (e.getActionCommand() == "Reset Walkers") {
            Game.instance.reset(3);
        }
    }
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if(!source.getValueIsAdjusting()) {
            double generalWeighted = (double)source.getValue() / 100;
            WalkerWeighted02.distWeight = 1 - generalWeighted;
            WalkerWeighted02.grassWeight = generalWeighted;
        }
    }
}
