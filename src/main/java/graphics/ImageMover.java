import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** ImageMover: Move one image around */
public class ImageMover extends Applet implements ActionListener {
	Sprite s;
	Panel p;
	Image img;

    public void init() {
		String imgName = getParameter("imagefile");
		if (imgName == null) 
			throw new IllegalArgumentException("imagefile parameter required");
		String orientation = getParameter("orientation");
		if (imgName == null) 
			throw new IllegalArgumentException("orientation parameter required");
		if (orientation.equalsIgnoreCase("horizontal")
			mode = HORIZONTAL;
		else if (orientation.equalsIgnoreCase("vertical")
			mode = VERTICAL;
		else
			mode = DIAGONAL;
		img = getImage(getCodeBase(), imgName);
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(img, 0);
		try {
			mt.waitForID(0);
		} catch(InterruptedException e) {
			throw new IllegalArgumentException(
				"InterruptedException while loading image " + imgName);
		}
		if (mt.isErrorID(0)) {
			throw new IllegalArgumentException(
				"Couldn't load image " + imgName);
		}
		Sprite s = new Sprite(this, img);
		this.add(s);
    }

    public void stop() {
		s.stop();
    }
}

