package performance;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/** Just a Frame
 */
public class GUIPerf extends JFrame {

	private static final long serialVersionUID = -3089466980388235513L;

	/** Construct the object including its GUI */
	public GUIPerf() {
		super("GUIPerf");
		Container cp = getContentPane();
		final JButton quitButton = new JButton("Exit");

		cp.add(quitButton);

		// Set up so that "Close" will exit the program, 
		// not just close the JFrame.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// This "action handler" will be explained later in the chapter.
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
			
		pack();
		setLocation(500, 400);
	}
	public static void main(String[] args) {
		long t0 = System.currentTimeMillis();
		new GUIPerf().setVisible(true);
		long t1 = System.currentTimeMillis();

                long runTime = t1 - t0;
		System.err.println(
                         "runTime="  + Double.toString(runTime/1000D));
		System.exit(0);
	}
}
