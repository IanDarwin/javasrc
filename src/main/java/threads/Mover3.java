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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** Extend Mover by adding the ability to start and stop the thread
 * just by clocking the mouse.
 */
public class Mover3 extends Mover implements MouseListener {

	protected volatile boolean threadSuspended;

	public void init() {
		System.out.println("Mover3 starting");
		super.init();
		addMouseListener(this);
		System.out.println("Mover3 initted");
	}

	public synchronized void mousePressed(MouseEvent e) {
		e.consume();

		System.out.println("mousePressed");

		threadSuspended = !threadSuspended;

		if (!threadSuspended)
			notifyAll();
	}

	/* Compute new position for the image, and ask it to be painted */
	public void run() {
		int w = getSize().width;
		while (!done) {
			try {
                Thread.sleep(interval);

                synchronized(this) {
                    while (threadSuspended)
                        wait();
                }

			} catch (InterruptedException canthappen) {
				// Do nothing
			}
			if (offset++ > w)
				offset = 0;
			repaint();
		}
	}

	// Methods required by MouseListener, but not interesting.
	public void mouseEntered(java.awt.event.MouseEvent $1) {
		// nothing
	}
	public void mouseExited(java.awt.event.MouseEvent $1) {
		// nothing
	}
	public void mouseReleased(MouseEvent e) {
		// nothing 
	}
	public void mouseClicked(MouseEvent e) {
		// nothing 
	}
}
