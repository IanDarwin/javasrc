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
import java.awt.Graphics;

/** Animator - move a rectangle diaganolly across the screen
 * using a Thread.
 */
public class Animator extends Applet implements Runnable {
	/** Where to draw the moving image: x coordinate */
	int x;
	/** Where to draw the moving image: y coordinate */
	int y;
	/** This is set true to stop the animation thread. */
	boolean done;

	/** Called by the browser to start the page */
	public void start() {
		done = false;
		new Thread(this).start();
	}

	/** Called by the browser when the user moves away from the page */
	public void stop() {
		done = true;
	}

	/**
	 * Move the rectangle around the screen at a 45-degree angle.
	 * Called by the Thread when there is CPU time available for me.
	 */
    public synchronized void run() {
		// Get the framesize
		int width = getSize().width;
		int height = getSize().height;

		// Start at a random location in it.
		x = (int)(Math.random() * width);
		y = (int)(Math.random() * height);

		while (!done) {
			// Obtain current size, in case resized.
			width = getSize().width;
			height = getSize().height;
			// Did we go off the deep end? :-)
			if (x++ >= width)
				x=0;	// return to shallow end 
			if (y++ >= height)
				y=0;
			repaint();		// Tell AWT to call our paint().
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	/** paint -- just draw our image at its current location */
    public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 10, 10);
    }
}
