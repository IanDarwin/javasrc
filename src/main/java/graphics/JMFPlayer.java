import java.applet.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.media.*;

/**
 * Demonstrate simple code to play a movie with Java Media Framework.
 */
public class JMFPlayer extends Applet implements ControllerListener {

    /** The player object */
    Player thePlayer = null;
    
    /** The visual component (if any) */
    Component visualComponent = null;
    /** The default control component */
    Component controlComponent = null;

	/** Initialize the player object and the GUI. */
	public void init() {
		setLayout(new BorderLayout());
		String mediaFile = getParameter("MOVIE");
		URL theURL;
		try {
			theURL = new URL(getDocumentBase(), mediaFile);
			thePlayer = Manager.createPlayer(theURL);
			thePlayer.addControllerListener(this);
		} catch (Exception e) {
			System.err.println("Got exception " + e);
			return;
		}
		System.out.println("theURL = " + theURL);
	}

	/** Called from the Browser when the page is ready to go. */
	public void start() {
		thePlayer.start();
	}

	/** Called from the Browser when the page is being vacated. */
	public void stop() {
		thePlayer.stop();
		thePlayer.deallocate();
	}

	/** Called from the Browser (when the applet is being un-cached?). */
	public void destroy() {
		thePlayer.close();
	}

	/** Called by JMF when the Player is ready to go. */
	public synchronized void controllerUpdate(ControllerEvent event) {
		System.out.println("controllerUpdate(" + event + ")");
		if (event instanceof RealizeCompleteEvent) {
			if ((visualComponent = thePlayer.getVisualComponent()) != null)
				add("Center", visualComponent);
			if ((controlComponent = 
				thePlayer.getControlPanelComponent()) != null)
				add("South", controlComponent);
			validate();
		}
	}
}
