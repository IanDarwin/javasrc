import java.awt.*;
import java.awt.event.*;

/** Template standalone GUI application */
public class YesNoDlgTest extends Frame {
	boolean unsavedChanges = false;
	Button quitButton;

	/** Construct the object including its GUI */
	public YesNoDlgTest() {
		super("YesNoDlgTest");
		setLayout(new FlowLayout());
		add(new Label("Hello, and welcome to the world of Java"));
		add(quitButton = new Button("Test"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new YesNoDialog(YesNoDlgTest.this, "Testing", "Which Microsoft ad do you want to ignore today?", "All", "None", "Cancel").setVisible(true);
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		pack();
	}
 
	/** "main program" method */
	public static void main(String[] av) {
		// create a YesNoDlgTest object
		YesNoDlgTest j = new YesNoDlgTest();
		// send message telling it to show up
		j.setVisible(true);
	}
}

