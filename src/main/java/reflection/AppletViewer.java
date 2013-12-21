package reflection;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/*
 * AppletViewer - a simple Applet Viewer program.
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class AppletViewer {
	/** The main Frame of this program */
	JFrame f;
	/** The AppletAdapter (gives AppletStub, AppletContext, showStatus) */
	static AppletAdapter aa = null;
	/** The name of the Applet subclass */
	String appName = null;
	/** The Class for the actual applet type */
	Class<?> ac = null;
	/** The Applet instance we are running, or null. Can not be a JApplet
	 * until all the entire world is converted to JApplet. */
	Applet ai = null;
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

		f = new JFrame("AppletViewer");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});
		Container cp = f.getContentPane();
		cp.setLayout(new BorderLayout());

		// Instantiate the AppletAdapter which gives us
		// AppletStub and AppletContext.
		if (aa == null)
			aa = new AppletAdapter();

		// The AppletAdapter also gives us showStatus.
		// Therefore, must add() it very early on, since the Applet's
		// Constructor or its init() may use showStatus()
		cp.add(BorderLayout.SOUTH, aa);

		showStatus("Loading Applet " + appName);

		loadApplet(appName , WIDTH, HEIGHT);	// sets ac and ai
		if (ai == null)
			return;

		// Now right away, tell the Applet how to find showStatus et al.
		ai.setStub(aa);

		// Connect the Applet to the Frame.
		cp.add(BorderLayout.CENTER, ai);

		Dimension d = ai.getSize();
		d.height += aa.getSize().height;
		f.setSize(d);
		f.setVisible(true);		// make the Frame and all in it appear

		showStatus("Applet " + appName + " loaded");

		// Here we pretend to be a browser!
		ai.init();
		ai.start();
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
			ac = Class.forName(appletName);
			// Construct an instance (as if using no-argument constructor)
			ai = (Applet) ac.newInstance();
		} catch(ClassNotFoundException e) {
			showStatus("Applet subclass " + appletName + " did not load");
			return;
		} catch (Exception e ){
			showStatus("Applet " + appletName + " did not instantiate");
			return;
		}
		ai.setSize(w, h);
	}

	public void showStatus(String s) {
		aa.getAppletContext().showStatus(s);
	}
}
// END main
