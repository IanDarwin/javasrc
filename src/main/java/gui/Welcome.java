import javax.swing.*;
import java.awt.*;

/** This program displays a simple pre-fab welcome sign.
 */
public class Welcome extends JComponent {

	/** The main program or entry point. */
	public static void main(String[] args) {
		JFrame f = new JFrame("Welcome");
		f.getContentPane().add(new Welcome());
		f.pack();
		f.setVisible(true);
	}

	/** The font used to display the text */
	Font f;

	/** Construct the program */
	public Welcome() {
		f = new Font("Helvetica", Font.BOLD, 100);
		setBackground(Color.white);
	}

	/** Do the drawing (called automagically by window system when needed) */
	public void paint(Graphics g) {
		g.setColor(Color.white);
		Dimension d = getSize();
		g.fill3DRect(0, 0, d.width-1, d.height-1, true);
		g.setFont(f);
		g.setColor(Color.red);
		g.drawString("Welcome", 100, 150);
		g.drawString("to",	240, 275);
		g.drawString("Java",	160, 400);
	}

	/** Say how big we'd like to be (value hardcoded for now) */
	public Dimension getPreferredSize() {
		return new Dimension(700,500);
	}
}