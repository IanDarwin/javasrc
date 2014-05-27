package graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Manager;
import javax.media.Player;
import javax.media.RealizeCompleteEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Demonstrate simple code to play a movie with Java Media Framework.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
@SuppressWarnings("serial")
// BEGIN main
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
		thePlayer.start();		// start playing
	}

	/** Called to stop the audio, as from a Stop button or menuitem */
	public void stop() {
		if (thePlayer == null)
			return;
		thePlayer.stop();		// stop playing!
		thePlayer.deallocate();	// free system resources
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
			// resize the main window
			if (parentFrame != null) {
				parentFrame.pack();
				parentFrame.setTitle(mediaName);
			}
		}
	}

	public static void main(String[] argv) {
		JFrame f = new JFrame("JMF Player Demo");
		Container frameCP = f.getContentPane();
		final String musicURL = argv.length == 0 ?
				"file:/home/ian/Music/Classical/Rachmaninoff Prelude C_ min.mp3" :
				argv[0];
		JMFPlayer p = new JMFPlayer(f, musicURL);
		frameCP.add(BorderLayout.CENTER, p);
		f.setSize(200, 200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
// END main
