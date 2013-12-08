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
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Threaded Labels: each instance of this class
 * runs its own thread, with a counter that counts down to zero.
 * <br/>Older version	0.5, July 1997, for JDK1.1
 * @author	Ian Darwin
 */
public class ThreadsOne extends Applet implements Runnable {
	Label lab;	
	Button but;
	Thread t;
	int count;

	/** run() - do the work of the thread. Might get confused if
	 * the user pushes the button a second time before we finish.
	 */
	public void run() {
		int c = count;
		while (c-- > 0) {
			lab.setText(Integer.toString(c));
			try {
				Thread.sleep(100);	// 100 msec
			} catch (Exception e) {
				return;
			}
		}
		System.out.println("All done");
	}

	/** Set the number of times the counter is to decrement */
	public void setCount(int i) {
		count = i;
	}

	/** init() [from Applet] - set up the GUI */
	public void init() {

		// Create the start button.
		add(but = new Button("Start"));
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			t = new Thread(ThreadsOne.this);
			t.start();
			}
		});

		// Make label large enough to hold three-digit number
		add(lab = new Label("000"));
	
		// Provide a count from the PARAM, default to 100.
		String snum;
		if ((snum = getParameter("count")) != null)
			setCount(Integer.parseInt(snum));
		else
			setCount(100);
	}
}
