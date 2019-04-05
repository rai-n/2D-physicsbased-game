package game.View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Panel displaying a coloured circle.
 */
public class ViewPanel extends JPanel implements ChangeListener {


    public ViewPanel() {
        super();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(240, 320));
    }
    
    /**
     * Paint the panel.
     * This method is invoked by the Java graphical system.
     * @param g the graphical context (supplied by the AWT)
     */
    @Override
    protected void paintComponent(Graphics g) {
        // clear panel to background colour
        super.paintComponent(g);
    }
    
    /**
     * Notify the view of a change in the model, so that the view should
     * be repainted.
     * @param e description of the change
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }

}
