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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/** Stop a thread by closing a socket.
 */
// BEGIN main
public class StopClose extends Thread {
	protected Socket io;

	public void run() {
		try {
			io = new Socket("java.sun.com", 80);	// HTTP
			BufferedReader is = new BufferedReader(
				new InputStreamReader(io.getInputStream()));
			System.out.println("StopClose reading");

			// The following line will deadlock (intentionally), since HTTP 
			// enjoins the client to send a request (like "GET / HTTP/1.0")
			// and a null line, before reading the response.

			String line = is.readLine();	// DEADLOCK

			// Should only get out of the readLine if an interrupt
			// is thrown, as a result of closing the socket.

			// So we shouldn't get here, ever:
			System.out.printf("StopClose FINISHED after reading %s!?", line);
		} catch (IOException ex) {
			System.out.println("StopClose terminating: " + ex);
		}
	}

	public void shutDown() throws IOException {
		if (io != null) {
			// This is supposed to interrupt the waiting read.
			io.close();
		}
		System.out.println("StopClose.shutDown() completed");
	}

	public static void main(String[] args) 
	throws InterruptedException, IOException {
		StopClose t = new StopClose();
		t.start();
		Thread.sleep(1000*5);
		t.shutDown();
	}
}
// END main
