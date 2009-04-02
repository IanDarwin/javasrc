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

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/** A failed attempt at catching interrupts thrown in the GUI
 * event thread.
 */
public class CatchMulti {
	public static void main(String[] args) {
		try {
			// Create the GUI, will implicitly create and start a GUI thread
			new ThrowAWT().setVisible(true); 
			// Wait a while.
			Thread.sleep(1000 * 20);
			// Create and start a thread that gives 
			// an ArrayIndexOutOfBoundsException
			new ThrowIndex().start();
		} catch (Exception ex) {
			System.out.println("Caught exception: " + ex);
			System.exit(1);
		}
	}
}

class ThrowAWT extends Frame {
	ThrowAWT() {
		Button b;
		add(b = new Button("Throw"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Deliberately throw a null pointer exception
				Random r = null;
				int i = r.nextInt();
			}
		});
		setBounds(60, 60, 60, 60);	// x, y, width, height
	}
}

class ThrowIndex extends Thread {
	public void run() {
		int[] data = new int[10];
		for (int i=1; i<=10; i++)
			data[i] = i;
	}
}
