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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/** Update a GUI after some slow operation completes.
 * Do it right, using the AWT event thread.
 */
public class UpdateSlowGUI extends JFrame {
	public static void main(String[] args) {
		// Create the GUI; will implicitly create and start AWT GUI thread
		new UpdateSlowGUI().setVisible(true); 
	}

	public UpdateSlowGUI() {
		super("UpdateSlowGUI");
		final JButton b;
		final JLabel status;
		Container cp = getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cp.add(BorderLayout.CENTER, b = new JButton("Update..."));
		cp.add(BorderLayout.SOUTH, status = new JLabel("Ready"));
		b.addActionListener(new ActionListener() {
			// Pressed when the user wants to do the action...
			public void actionPerformed(ActionEvent evt) {
				status.setText("Busy...");
				b.setEnabled(false);
				Thread t = new Thread() {
					public void run() {
						// Simulate something slow, like a large DB query
						try {
							Thread.sleep(5000);
						} catch(InterruptedException ex) {
							return;
						}

						// OK, the long-running thing has finished.
						// Now go back into AWT/Swing to update the GUI
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								status.setText("Ready");
								b.setEnabled(true);
							}
						});
					}
				};
				t.start();
			}
		});
		setSize(150, 200);
		setLocation(200, 200);
	}
}
