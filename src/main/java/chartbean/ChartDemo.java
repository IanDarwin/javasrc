import java.awt.*;
import java.awt.event.*;

import com.darwinsys.charts.*;

/** Simple GUI driver for Chart, showing it used as a plain Component */
public class ChartTest extends Frame {
	boolean unsavedChanges = false;
	Button drawButton;
	Button quitButton;
	com.darwinsys.charts.Chart bean;

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create a ChartTest object
		ChartTest j = new ChartTest();
		// send message telling it to show up
		j.setVisible(true);
	}

	/** Construct the object including its GUI */
	public ChartTest() {
		super("ChartTest");
		setLayout(new FlowLayout());
		add(new Label("Simple demo of a Java Chart Bean"));
		add(quitButton = new Button("Exit"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		add(drawButton = new Button("Draw"));
		drawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bean.doDemo();
			}
		});

		add(bean = new com.darwinsys.charts.Chart("System Usage in 1999"));

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
			
		pack();
	}

	public Dimension getPreferredSize() {
		return new Dimension(400, 300);
	}
}
