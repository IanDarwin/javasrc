import java.applet.*;
import java.awt.*;

public class WalkingText extends Applet implements Runnable {
	protected String mesg = null;
	protected int  xloc, yloc, width, height, textWidth, textHeight;
	protected Thread t;
	protected boolean done = false;
	/** How long to nap for each move */
	protected int napTime = 150;

	/** Applet Initializer */
	public void init() {
		xloc = 0;
		width = getSize().width;
		height = getSize().height;

		if ((mesg = getParameter("text")) == null)
			mesg = "Hello World of Java";

		String pSize = getParameter("fontsize"); 
		if (pSize == null)
			pSize = "12";

		String fontName = getParameter("fontName"); 
		if (fontName == null)
			fontName = "Helvetica";

		// System.out.println("Font is " + pSize + " point " + fontName);
		Font f = new Font(fontName, Font.PLAIN, Integer.parseInt(pSize));
		setFont(f);

		FontMetrics fm = getToolkit().getFontMetrics(f);
		textWidth = fm.stringWidth(mesg);
		textHeight = fm.getHeight();
		// System.out.println("TextWidth " + textWidth + ", ht " + textHeight);

		// use textHeight in y coordinate calculation
		yloc = height - ((height-textHeight) / 2);
	}

	/** This is important: we create a thread, so we must kill it */
	public void stop() {
		done = true;
		t = null;
	}

	/** create the thread and start it. */
	public void start() {
		if (t == null)
			t = new Thread(this);
		done = false;
		t.start();
	}

	// Usage:
	public String[][] getParameterInfo() {
		String[][] params = {
			{ "text", "text", "Text to walk across the screen" },
			{ "fontName", "text", "Name of font to display in" },
			{ "fontsize", "int", "How big to make the text" },
		};

		return params;
	}


	/** Run is called by the Thread class when there is work to do */
	public void run() {
		while (!done) {
			if ((xloc+=5) > getSize().width)
				xloc = 0;
			repaint();
			try {
				Thread.sleep(napTime);
			} catch (Exception e) {
				System.out.println("Who dares to interrupt my Sleep()? " + e);
			};
		}
	}

	/** Paint is called by Applet to redraw the screen */
	public void paint(Graphics g) {
		g.drawString(mesg, xloc, yloc);
		// if ((xloc + textWidth) > getSize().width) {
		// 	int negLoc = textWidth-(width-xloc);
		// 	System.out.println("xloc, textWidth, negLoc: " + xloc + "," +
		// 			textWidth + ", " + negLoc);
		// 	g.drawString(mesg, negLoc, yloc);
		// }
	}
}
