import com.darwinsys.util.WindowCloser;

import java.applet.*;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.media.*;

/**
 * Demonstrate simple code to play a movie with Java Media Framework.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class JMFPlayer extends JPanel implements ControllerListener {

    /** The player object */
    Player thePlayer = null;
    /** The parent Frame we are in. */
	JFrame parentFrame = null;
	/** Our contentpane */
	Container cp;
    /** The visual component (if any) */
    Component visualComponent = null;
    /** The default control component (if any) */
    Component controlComponent = null;
	/** The name of this instance's media file. */
	String mediaName;
	/** The URL representing this media file. */
	URL theURL;

	/** Construct the player object and the GUI. */
	public JMFPlayer(JFrame pf, String media) {
		parentFrame = pf;
		mediaName = media;
		// cp = getContentPane();
		cp = this;
		cp.setLayout(new BorderLayout());
		try {
			theURL = new URL(getClass().getResource("."), mediaName);
			thePlayer = Manager.createPlayer(theURL);
			thePlayer.addControllerListener(this);
		} catch (MalformedURLException e) {
			System.err.println("JMF URL creation error: " + e);
		} catch (Exception e) {
			System.err.println("JMF Player creation error: " + e);
			return;
		}
		System.out.println("theURL = " + theURL);

		// Start the player: this will notify our ControllerListener.
		thePlayer.start();
	}

	/** Called to stop the audio, as from a Stop button or menuitem */
	public void stop() {
		if (thePlayer == null)
			return;
		thePlayer.stop();
		thePlayer.deallocate();
	}

	/** Called when we are really finished (as from an Exit button). */
	public void destroy() {
		if (thePlayer == null)
			return;
		thePlayer.close();
	}

	/** Called by JMF when the Player has something to tell us about. */
	public synchronized void controllerUpdate(ControllerEvent event) {
		// System.out.println("controllerUpdate(" + event + ")");
		if (event instanceof RealizeCompleteEvent) {
			if ((visualComponent = thePlayer.getVisualComponent()) != null)
					cp.add(BorderLayout.CENTER, visualComponent);
			if ((controlComponent = 
				thePlayer.getControlPanelComponent()) != null)
					cp.add(BorderLayout.SOUTH, controlComponent);
			// re-size the main window
			if (parentFrame != null) {
				parentFrame.pack();
				parentFrame.setTitle(mediaName);
			}
		}
	}

	public static void main(String[] argv) {
		JFrame f = new JFrame("JMF Player Demo");
		Container frameCP = f.getContentPane();
		JMFPlayer p = new JMFPlayer(f, argv.length == 0 ?
			"file:///C:/music/midi/beet5th.mid" : argv[0]);
		frameCP.add(BorderLayout.CENTER, p);
		f.setSize(200, 200);
		f.setVisible(true);
		f.addWindowListener(new WindowCloser(f, true));
	}
}
