package threads;

/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
 * $Id$
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *        This product includes software developed by Ian F. Darwin.
 * 4. Neither the name of the author nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating (and standardizing) the Java 
 * language and environment is gratefully acknowledged.
 * 
 * The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
 * inventing predecessor languages C and C++ is also gratefully acknowledged.
 */

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

/**
 * Mover -- move an image, slowly.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public class Mover extends Applet implements Runnable {
	/** The done or not done flag */
	protected volatile boolean done = false;
	/** The current Image, or null */
	protected Image img;
	/** The NAME of the current Image, or null */
	protected String imageName;
	/** the size of the current image */
	protected int imgWid = 0, imgHt = 0;
	/** DEFAULT msec between updates */
	public final static int DEFAULT_INTERVAL = 40;
	/** msec between updates */
	protected int interval = DEFAULT_INTERVAL;
	/** Where we are */
	protected int offset = 0;
	/** The Thread that keeps us ticking */
	protected Thread ticker;

	// THESE CONSTRUCTORS ARE ONLY NEEDED FOR TESTING AS A NON-APPLET
	// YOU DON'T NEED THEM FOR AN APPLET-ONLY SOLUTION.

	/** Construct a Mover, given an Image and using a default pause interval. */
	public Mover(String imgName) {
		this(imgName, DEFAULT_INTERVAL);
	}

	/** Construct a Mover, given an Image and a pause interval. */
	public Mover(String imgName, int pauseInt) {
		interval = pauseInt;
		imageName = imgName;
		init();
	}

	/** Since we have the above Constructors, we need this one for Applet */
	public Mover() {
	}

	/** Setup a Mover applet. */
	public void init() {
		/** If set by non-default constructor, don't call Applet methods! */
		if (imageName == null)
			imageName = getParameter("imagename");
		if (imageName == null)
			imageName = "Mover.gif";
		System.out.println("imageName = " + imageName);
		setImage(imageName);
		System.out.println("setImage done");
	}

	public void start() {
		done = false;
		startThread();
	}

	protected void startThread() {
		ticker = new Thread(this);
		ticker.setName("Ticker animation");
		ticker.setPriority(Thread.MAX_PRIORITY);
		ticker.start();
	}

	public void stop() {
		done = true;
		ticker = null;
	}

	/** Set the image to the given file */
	public void setImage(String fn) {
		if (fn == null)
			return;

		imgWid = imgHt = 0;
		offset = 0;

		// img = Toolkit.getDefaultToolkit().getImage(fn);
		img = getImage(getDocumentBase(), fn);

		// Use a MediaTracker to show the "best"? way of waiting
		// for an image to load, and how to check for errors.
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(img, 0);
		try {
			mt.waitForID(0);
		} catch(InterruptedException e) {
			throw new IllegalArgumentException(
				"Unexpected InterruptedException");
		}
		if (mt.isErrorID(0)) {
			throw new IllegalArgumentException(
				"Couldn't load image " + fn);
		}
	}

	/** Return how big we'd like to be. If image loaded, use its size.
	 * If not, use an arbitrary default.
	 */
	public Dimension getPreferredSize() {
		if (img == null || img.getWidth(this) < 0 || img.getHeight(this) < 0)
			return new Dimension(100, 100);
		return new Dimension(imgWid * 20, imgHt);
	}

	/** Pick a new position for the image, and ask it to be painted */
	public void run() {
		int w = getSize().width;
		// System.out.println("Width = " + w);
		while (!done) {
			if (offset++ > w)
				offset = 0;
			try {
				Thread.sleep(interval);
				repaint();
			} catch (InterruptedException canthappen) {
			}
		}
	}

	/** Actually draw the Image onto the screen */
	public void paint(Graphics g) {
		if (img == null) {
			g.setColor(Color.red);
			g.fillRect(0, 0, getSize().width, getSize().height);
		} else
			g.drawImage(img, offset, 0, this);
	}

	/** "main program" method - construct and show */
	public static void main(String[] av) {
	
		final Frame f = new Frame("Mover Demo");
		final Mover m = new Mover("mover.gif", 10);
		f.add(m);
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.init();
		m.start();
		f.pack();
		f.setVisible(true);
	}
}
