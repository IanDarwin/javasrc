import java.applet.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.media.*;

/**
 * Demonstrate simple code to play a movie with Java Media Framework.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class JMFPlayer extends Applet implements ControllerListener {

    /** The player object */
    Player thePlayer = null;
    
    /** The visual component (if any) */
    Component visualComponent = null;
    /** The default control component (if any) */
    Component controlComponent = null;

	/** Initialize the player object and the GUI. */
	public void init() {
		setLayout(new BorderLayout());
		String mediaFile = getParameter("MEDIA");
		URL theURL;
		try {
			theURL = new URL(getDocumentBase(), mediaFile);
			thePlayer = Manager.createPlayer(theURL);
			thePlayer.addControllerListener(this);
		} catch (Exception e) {
			System.err.println("JMF Player creation error: " + e);
			showStatus("JMF Player creation error.");
			return;
		}
		System.out.println("theURL = " + theURL);
	}

	/** Called from the Browser when the page is ready to go. */
	public void start() {
		if (thePlayer == null)
			return;
		thePlayer.start();
	}

	/** Called from the Browser when the page is being vacated. */
	public void stop() {
		if (thePlayer == null)
			return;
		thePlayer.stop();
		thePlayer.deallocate();
	}

	/** Called from the Browser (when the applet is being un-cached?). */
	public void destroy() {
		if (thePlayer == null)
			return;
		thePlayer.close();
	}

	/** Called by JMF when the Player has something to tell us about. */
	public synchronized void controllerUpdate(ControllerEvent event) {
		if (event instanceof RealizeCompleteEvent) {
			System.out.println("controllerUpdate(" + event + ")");
			if ((visualComponent = thePlayer.getVisualComponent()) != null)
				add("Center", visualComponent);
			if ((controlComponent = 
				thePlayer.getControlPanelComponent()) != null)
				add("South", controlComponent);
			validate();
		}
	}
}
