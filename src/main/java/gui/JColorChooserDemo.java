import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* 
 * Colors - demo of Swing JColorChooser.
 * Swing's JColorChooser can be used in three ways:
 * <UL><LI>Construct it and place it in a panel;
 * <LI>Call its ConstructDialog() and get a JDialog back
 * <LI>Call its showDialog() and get back the chosen color
 * </UL>
 * <P>We use the last method, as it's the simplest, and is how
 * you'd most likely use it in a real application.
 *
 * @version $Id$
 */
public class Colors extends JFrame
{
	/** A canvas to display the color in. */
    MyCanvas demo;
	/** The latest chosen Color */
	Color lastChosen;

	/** Constructor - set up the entire GUI for this program */
    public Colors() {
        super("Linux Journal Color Demo");
		Container cp = getContentPane();
        JButton jButton;
        cp.add(BorderLayout.NORTH, jButton = new JButton("Choose Color..."));
		jButton.setToolTipText("Click here to see the Color Chooser");
        jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent)
			{
				Color ch = JColorChooser.showDialog(
					Colors.this,				// parent
					"Linux Demo Color Popup",	// title
					getBackground());			// default
				if (ch != null)
					demo.setColor(ch);
			}
		});
        cp.add(BorderLayout.CENTER, demo = new MyCanvas(200, 100));
		demo.setToolTipText("This is the last color you chose");
        pack();
        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent)
			{
				System.exit(0);
			}
		});
	}

	/** good old main */
    public static void main(String argv[])
    {
        new Colors().setVisible(true);
    }
}

/** A helper class that just paints itself
 * in the given color, and tries to stay at the
 * size it was requested in the Constructor.
 */
class MyCanvas extends JComponent {
	int width, height;
	Color col = getBackground();

	/** Constructor */
	MyCanvas(int w, int h) {
		width = w;
		height = h;
		setSize(width, height);
	}

	/** Called by the program to set the color */
	void setColor(Color c) {
		col = c;
		repaint();
	}

	/** Called by AWT whenever the screen needs painting */
	public void paint(Graphics g) {
		Dimension d = getSize();
		g.setColor(col);
		g.fillRect(0, 0, d.width-1, d.height-1);
		g.setColor(Color.black);
		g.drawString("Your color", 20, d.height/2);
	}

	/** Called by AWT layout when it needs to know */
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}
