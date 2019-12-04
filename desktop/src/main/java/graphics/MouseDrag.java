package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

/** MouseDrag -- implement simple mouse drag in a window--
 * Draws a rectangle whose size varies while you drag its
 * lower-right corner. The given image file is used
 * as a background image for the window.
 */
public class MouseDrag extends Component
		implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 8557207971117778751L;
	/** The Image we are to paint */
	Image curImage;
	/** Kludge for showStatus */
	Label status;
	/** true if we are in drag */
	boolean inDrag = false;
	/** starting location of a drag */
	int startX = -1, startY = -1;
	/** current location of a drag */
	int curX = -1, curY = -1;

	// "main" method
	public static void main(String[] av) {
		JFrame f = new JFrame("Mouse Dragger");
		Container cp = f.getContentPane();

		Image im = Toolkit.getDefaultToolkit().getImage(
				av.length == 1 ? av[0] : "graphics/duke.gif");

		// create a MouseDrag object
		MouseDrag j = new MouseDrag(im);

		cp.setLayout(new BorderLayout());
		cp.add(BorderLayout.NORTH,
			new Label("Hello, and welcome to the world of Java"));
		cp.add(BorderLayout.CENTER, j);
		cp.add(BorderLayout.SOUTH, j.status = new Label());
		j.status.setSize(
				f.getSize().width, j.status.getSize().height);
		f.setSize(300, 300);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// "Constructor" - creates the object
	public MouseDrag(Image i) {
		super();
		curImage = i;
		setSize(300,200);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void showStatus(String s) {
		status.setText(s);
	}

	// Five methods from MouseListener:
	/** Called when the mouse has been clicked on a component. */
	public void mouseClicked(MouseEvent e)  {
	}

	/** Called when the mouse enters a component. */
	public void mouseEntered(MouseEvent e)  {
	}

	/** Called when the mouse exits a component. */
	public void mouseExited(MouseEvent e)  {
	}

	/** Called when the mouse has been pressed. */
	public void mousePressed(MouseEvent e)  {
		startX = e.getX();
		startY = e.getY();
		System.err.println("mousePressed at [" + startX + "," + startY + "]");
		inDrag = true;
	}

	/** Called when the mouse has been released. */
	public void mouseReleased(MouseEvent e)  {
		inDrag = false;
		System.err.println("SELECTION IS " + startX + "," +
			startY + " to " + curX + "," + curY);
	}

	// And two methods from MouseMotionListener:
	public void mouseDragged(MouseEvent e) {
		curX = e.getX();
		curY = e.getY();
		showStatus("mouse Dragged to [" + curX + "," + curY + "]");
		if (inDrag) {
			repaint();
		}
	}

	public void mouseMoved(MouseEvent e) {
		showStatus("mouse Moved to " + e.getPoint());
	}

	@Override
	public void paint(Graphics g) {
		int w = curX - startX, h = curY - startY;
		Dimension d = getSize();
		g.drawImage(curImage, 0, 0, d.width, d.height, this);
		if (startX < 0 || startY < 0)
			return;
		System.err.println("paint:drawRect @[" + startX +"," + startY +
			"] size " + w + "x" + h);
		g.setColor(Color.red);
		g.fillRect(startX, startY, w, h);
	}



}
