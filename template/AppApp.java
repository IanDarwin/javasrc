package template;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Example of a class that can be used as an Applet or an Application
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version #Id$
 */
public class AppApp extends Applet {
	boolean inAnApplet = true;
	Label status;		// for Application showStatus()

	public void init() {
		add(new Label("This is my demo Applet"));
		showStatus("My applet is running");
	}

	public static void main(String[] av) {
		AppApp app = new AppApp();
		final Frame f = new Frame("AppApp Demo");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.setVisible(false);
				f.dispose();
			}
		});
		f.setLayout(new BorderLayout());
		app.inAnApplet = false;
		f.add("Center", app);
		// Must do this before init() since init() may use showStatus()
		f.add("South", app.status = new Label());
		f.setSize(300, 200);
		app.status.setSize(f.getSize().width, app.status.getSize().height);

		// Here we pretend to be a browser!
		// A fancier version would make an AppletStub and pass it
		// into the Applet with getAppletStub().
		app.init();
		app.start();

		f.setVisible(true);
	}
	public void showStatus(String s) {
		 if (inAnApplet) {
			 super.showStatus(s);	// call version in Browser
		 } else {
			 status.setText(s);		// do it yourself.
		 }
	}
}
