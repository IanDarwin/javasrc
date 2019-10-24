package chartbean;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** Simple GUI driver for Chart, showing it used as a plain Component */
public class ChartDemo extends Frame {

	private static final long serialVersionUID = -5193600104234919336L;
	boolean unsavedChanges = false;
	Button drawButton;
	Button quitButton;
	Chart bean;

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create a ChartTest object
		ChartDemo j = new ChartDemo();
		// send message telling it to show up
		j.setVisible(true);
	}

	/** Construct the object including its GUI */
	public ChartDemo() {
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

		add(bean = new Chart("System Usage in 1999"));

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
