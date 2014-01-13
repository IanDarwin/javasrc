package applet;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;

/** AppletMethods -- show stop/start and AudioClip methods */

public class AppletMethods extends Applet {

	private static final long serialVersionUID = -4376117045074079151L;
	/** AudioClip object, used to load and play a sound file. */
	transient AudioClip snd = null;

	/** Initialize the sound file object and the GUI. */
	public void init() {
		System.out.println("In AppletMethods.init()");
		try {
			snd = getAudioClip(new URL(getCodeBase(), "laugh.au"));
		} catch (MalformedURLException e) {
			showStatus(e.toString());
		}
		setSize(200,100);	// take the place of a GUI
	}

	/** Called from the Browser when the page is ready to go. */
	public void start() {
		System.out.println("In AppletMethods.start()");
		if (snd != null)
			snd.play();	// loop() to be obnoxious...
	}

	/** Called from the Browser when the page is being vacated. */
	public void stop() {
		System.out.println("In AppletMethods.stop()");
		if (snd != null)
			snd.stop();	// stop play() or loop() 
	}

	/** Called from the Browser (when the applet is being un-cached?).
	 * Not actually used here, but the println will show when it's called.
	 */
	public void destroy() {
		System.out.println("In AppletMethods.destroy()");
	}

	public void paint(Graphics g) {
		g.drawString("Welcome to Java", 50, 50);
	}

	/** An alternate form of getParameter that lets
	 * you provide a default value, since this is so common.
	 */
	public String getParameter(String p, String def) {
		return getParameter(p)==null?def:getParameter(p);
	}
}
