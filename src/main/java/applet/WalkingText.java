package applet;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

public class WalkingText extends Applet implements Runnable {

	private static final long serialVersionUID = 3257567317243082032L;
	protected String mesg = null;
	protected int  xloc, yloc, width, height, textWidth, textHeight;
	protected Thread runnerThread;
	protected boolean done = false;
	/** How long to nap for each move */
	protected int napTime = 200;
	private Font theFont;
	private int xIncrement = 5;

	/**  Usage information */
	public String[][] getParameterInfo() {
		return new String[][]  {
			{ "text", "text", "Text to walk across the screen" },
			{ "fontName", "text", "Name of font to display in" },
			{ "fontsize", "int", "How big to make the text" },
			{ "xincrement", "int", "How far to move the text each tick" },
			{ "ticktime", "int", "How long (in mSec) to sleep after each move" },
		};
	}

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

		theFont = new Font(fontName, Font.PLAIN, Integer.parseInt(pSize));
		setFont(theFont);

		// Compute the width and height of the text.
		FontRenderContext fontRenderContext = ((Graphics2D)getGraphics()).getFontRenderContext();
		TextLayout tl = new TextLayout(mesg, getFont(), fontRenderContext);
		textWidth = (int)tl.getBounds().getHeight();
		textHeight = (int)tl.getBounds().getWidth();
	
		// System.out.println("TextWidth " + textWidth + ", ht " + textHeight);

		// use textHeight in y coordinate calculation
		yloc = height - ((height-textHeight) / 2);
		
		String xIncrString = getParameter("xincrement");
		if (xIncrString != null) {
			xIncrement = Integer.parseInt(xIncrString);
		}
		String sleepTimeString = getParameter("sleeptime");
		if (sleepTimeString != null) {
			napTime = Integer.parseInt(sleepTimeString);
		}
	}

	/** create the thread and start it. */
	public void start() {
		if (runnerThread == null)
			runnerThread = new Thread(this);
		done = false;
		runnerThread.start();
	}

	/** Important: we create a thread, so we must kill it */
	public void stop() {
		done = true;
		runnerThread = null;
	}

	/** Run is called by the Thread class  */
	public void run() {
		while (!done) {
			if ((xloc+=xIncrement ) > getSize().width)
				xloc = 0;
			repaint();
			try {
				Thread.sleep(napTime);
			} catch (Exception e) {
				throw new IllegalStateException("sleep() interrupted");
			}
		}
	}

	/** Paint is called by Applet to redraw the screen */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawString(mesg, xloc, yloc);
		// if ((xloc + textWidth) > getSize().width) {
		// 	int negLoc = textWidth-(width-xloc);
		// 	System.out.println("xloc, textWidth, negLoc: " + xloc + "," +
		// 			textWidth + ", " + negLoc);
		// 	g.drawString(mesg, negLoc, yloc);
		// }
	}
}
