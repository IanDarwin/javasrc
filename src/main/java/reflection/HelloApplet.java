import java.applet.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * HelloApplet is a simple applet that changes color when you click on a
 * Draw button.
 */
public class HelloApplet extends JApplet {
	boolean requested;

	/** init() is an Applet method called by the browser to initialize */
	public void init() {
		JButton b;
		requested = false;
		Container gp = (Container)getGlassPane();
		gp.setLayout(new FlowLayout());
		gp.add(b = new JButton("Draw"));
		gp.setVisible(true);
		b.addActionListener(new ActionListener() {
			/*  Button - toggle the state of the "requested" flag, to draw or
			 *  not to draw.
			 */
			public void actionPerformed(ActionEvent e) {
				String arg = e.getActionCommand();
				// Invert the state of the draw request.
				requested = !requested;
				repaint();
			}
		});
	}

	/** paint() is an AWT Component method, called when the 
	 *  component needs to be painted.
	 */
	public void paint(Graphics g) {
		/* If the Draw button has been pressed, draw something */
		int w = getSize().width, h=getSize().height;
		if (requested) {
			g.setColor(Color.yellow);
			g.fillRect(0, 0, w/2, h);
			g.setColor(Color.green);
			g.fillRect(w/2, 0, w, h);
			g.setColor(Color.black);
			showStatus("Welcome to Java!");
		} else {
			g.setColor(getBackground());
			g.fillRect(0, 0, w, h);
			showStatus("");
		}
	}
}
