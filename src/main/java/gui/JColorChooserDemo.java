import com.darwinsys.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* 
 * Colors - demo of Swing JColorChooser.
 * Swing's JColorChooser can be used in three ways:
 * <UL><LI>Construct it and place it in a panel;
 * <LI>Call its createDialog() and get a JDialog back
 * <LI>Call its showDialog() and get back the chosen color
 * </UL>
 * <P>We use the last method, as it's the simplest, and is how
 * you'd most likely use it in a real application.
 *
 * @version $Id$
 * Originally appeared in the Linux Journal, 1999.
 */
public class JColorDemo extends JFrame
{
	/** A canvas to display the color in. */
    JLabel demo;
	/** The latest chosen Color */
	Color lastChosen;

	/** Constructor - set up the entire GUI for this program */
    public JColorDemo() {
        super("Swing Color Demo");
		Container cp = getContentPane();
        JButton jButton;
        cp.add(BorderLayout.NORTH, jButton = new JButton("Change Color..."));
		jButton.setToolTipText("Click here to see the Color Chooser");
        jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent)
			{
				Color ch = JColorChooser.showDialog(
					JColorDemo.this,				// parent
					"Swing Demo Color Popup",	// title
					getBackground());			// default
				System.out.println("Your selected color is " + ch);
				if (ch != null) {
					demo.setBackground(ch);
					demo.repaint();
				}
			}
		});
        cp.add(BorderLayout.CENTER, demo = 
			new JLabel("Your One True Color", JLabel.CENTER));
		demo.setToolTipText("This is the last color you chose");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/** good old main */
    public static void main(String[] argv)
    {
        new JColorDemo().setVisible(true);
    }
}
