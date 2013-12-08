package jmx;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Simple demo of a JMX-managed bean in an application;
 * uses a JFrame-based demo because it will stick around, be visible,
 * and have a Quit button.
 * Must be started with -Dcom.sun.management.jmxremote
 * if it is to be accessed remotely; you can usually access local
 * MBeans even without this.
 */
public class JMXDemo extends JFrame {

	private static final long serialVersionUID = -3089464321038823513L;

	private final MyControllerMBean controller = new MyController();

	public static void main(String[] args) throws Exception {
		new JMXDemo().setVisible(true);
	}

	/** Construct the object including its GUI */
	public JMXDemo() throws Exception {
		super("JMXDemo");

		// Register the JMXBean (Controller) with JMX
		final MBeanServer platformMBeanServer =
			ManagementFactory.getPlatformMBeanServer();
		ObjectName beanName = new ObjectName(
			"jmx.demo:type=jmx.MyController,name=Controller");
		platformMBeanServer.registerMBean(controller, beanName);

		// Set up the GUI
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());

		JButton rebootButton;
		cp.add(rebootButton = new JButton("Reset"));
		rebootButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.reset();
			}
		});
		JButton quitButton;
		cp.add(quitButton = new JButton("Exit"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controller.shutDown();
			}
		});

		// Set up so that "Close" will exit the program,
		// not just close the JFrame.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setLocation(500, 400);
	}
}
