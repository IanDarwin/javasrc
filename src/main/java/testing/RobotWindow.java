package testing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** The AWT "Robot" class allows you to control the GUI by moving
 * the mouse, pressing the mouse, entering keys, etc.
 * In real life it would be used to test one component from
 * a generic testing harness; here I simply use it within the class.
 * <p>
 * TODO: split into generic test harness and "gui tester".
 * Read textfile listing class to test, events to generate, results to expect.
 * @author Ian Darwin
 * @since 1.3
 */
public class RobotWindow extends JFrame {
	public static void main(String[] args) {
		RobotWindow gui = new RobotWindow();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setLocation(0,0);
		gui.setVisible(true);
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException ex) {
			log("Cannot create Robot");
			System.exit(1);
		}
		// assert robot != null;
		exercise(gui, robot);
	}

	/** Exercise the GUI using the AWT Robot */
	static void exercise(Frame f, Robot r) {
		r.delay(1000);	// time for frame to become visible.
		log("Starting");
		r.mouseMove(50,30);
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
		r.waitForIdle();
		r.mouseMove(150,20);
		log("All done");
	}

	/** Construct the GUI */
	RobotWindow() {
		super("Robot Test");
		Container cp = getContentPane();
		JButton b;
		cp.add(b = new JButton("Button"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log("Button pressed!");
			}
		});
		pack();
	}

	static void log(String s) {
		System.out.println(s);
	}
}
