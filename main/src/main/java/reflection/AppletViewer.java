package reflection;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/*
 * AppletViewer - a simple Applet Viewer program, since most 
 * web browsers will no longer process Applets.
 * @author	Ian Darwin, https://darwinsys.com/
 */
public class AppletViewer {
	/** The main Frame of this program */
	JFrame jFrame;
	/** The AppletAdapter (gives AppletStub, AppletContext, showStatus) */
	static AppletAdapter adapter = null;
	/** The name of the Applet subclass */
	String appName = null;
	/** The Class for the actual applet type */
	Class<?> appletClass = null;
	/** The Applet instance we are running, or null. Can not be a JApplet
	 * until all the entire world is converted to JApplet. */
	Applet instance = null;
	/** The width of the Applet */
	final int WIDTH = 250;
	/** The height of the Applet */
	final int HEIGHT = 200;

	/** Main is where it all starts. 
	 * Construct the GUI. Load the Applet. Start it running.
	 */
	public static void main(String[] av) {
		new AppletViewer(av.length==0?"HelloApplet":av[0]);
	}

	/** Construct the GUI for an Applet Viewer */
	AppletViewer(String appName) {
		super();

		this.appName = appName;

		jFrame = new JFrame("AppletViewer");
		jFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				jFrame.setVisible(false);
				jFrame.dispose();
				System.exit(0);
			}
		});
		Container cp = jFrame.getContentPane();
		cp.setLayout(new BorderLayout());

		// Instantiate the AppletAdapter which gives us
		// AppletStub and AppletContext. Lazy evaluation
		// allows mock for testing.
		if (adapter == null) {
			adapter = new AppletAdapter();
		}

		// The AppletAdapter also gives us showStatus.
		// Therefore, must add() it very early on, since the Applet's
		// Constructor or its init() may use showStatus()
		cp.add(BorderLayout.SOUTH, adapter);

		showStatus("Loading Applet " + appName);

		loadApplet(appName , WIDTH, HEIGHT);	// sets appletClass and instance
		if (instance == null) {
			JOptionPane.showMessageDialog(jFrame,
					"Sorry, Applet " + appName + " failed to load",
					"Oops", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Now right away, tell the Applet how to find showStatus et al.
		instance.setStub(adapter);

		// Connect the Applet to the Frame.
		cp.add(BorderLayout.CENTER, instance);

		Dimension d = instance.getSize();
		d.height += adapter.getSize().height;
		jFrame.setSize(d);
		jFrame.setVisible(true);		// make the Frame and all in it appear

		showStatus("Applet " + appName + " loaded");

		// Here we pretend to be a browser!
		instance.init();
		instance.start();
	}

	/*
	 * Load the Applet into memory. Should do caching.
	 */
	void loadApplet(String appletName, int w, int h) {
		// appletName = ... extract from the HTML CODE= somehow ...;
		// width = 		ditto
		// height = 		ditto
		try {
			// get a Class object for the Applet subclass
			appletClass = Class.forName(appletName);
			// Construct an instance (as if using no-argument constructor)
			instance = (Applet) appletClass.newInstance();
		} catch(ClassNotFoundException e) {
			showStatus("Applet subclass " + appletName + " did not load");
			return;
		} catch (Exception e ){
			showStatus("Applet " + appletName + " did not instantiate");
			return;
		}
		instance.setSize(w, h);
	}

	public void showStatus(String s) {
		adapter.getAppletContext().showStatus(s);
	}
}
