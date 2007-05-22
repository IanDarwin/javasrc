package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/** Contrived program showing how to catch Exceptions
 * that occur on the event dispatching thread. Define the System 
 * property "sun.awt.exception.handler" to name a class with a method
 * <pre>public void handle(Throwable t)</pre>.
 * <p>
 * That really is all you have to do to catch GUI Exceptions.
 * But it may change at any time (hence the name sun.awt...).
 * @author Ian Darwin.
 */
public class ErrorHandlerTest extends JFrame {

	/** A fairly banal GUI, just to show interaction.
	 */
	ErrorHandlerTest() {
		super("GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		JButton bx = new JButton("Throw!");
		bx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				throw new IllegalArgumentException("foo");
			}
		});
		cp.add(bx);
		setBounds(200, 200, 200, 100);
	}

	public static void main(String[] args) {
		// Tell AWT to invoke my Handler.
		System.setProperty("sun.awt.exception.handler", "ErrorHandler");

		// Now create and show the GUI.
		new ErrorHandlerTest().setVisible(true);
	}
}
