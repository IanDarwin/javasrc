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

// import - none

/**
 * Timer to interrupt a long-running call (like read, write, hence the name).
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class IOTimer extends Thread {
	/** The Thread we are timing. */
	Thread timedThread;
	/** How long to give the Thread */
	int time;
	public IOTimer(Thread t, int mSec) {
		System.out.println("IOTimer.<init>: Thread " + t);
		setPriority(MAX_PRIORITY);
		timedThread = t;
		time = mSec;
	}

	public void run() {
		System.out.println("About to sleep for " + time + "mSec");
		try {
			sleep(time);
		} catch (InterruptedException ie) {
			System.out.println("You interrupted my sleep!");
			return;
		}
		// if we are still here, the timer went off
		System.out.println("Will now interrupt sleep of " + timedThread);
		timedThread.interrupt();
	}

	/** Simple test case */
	public static void main(String[] ap) {
		byte b[] = new byte[10];
		System.out.println("Creating IOTimer");
		new IOTimer(Thread.currentThread(), 1000).start();
		System.out.println("Starting read");
		try {
			// This read will block, unless you type something in
			// the console window (and you have to be pretty quick!).
			System.in.read(b);

		// Cannot catch InterruptedException, as read() doesn't declare it.
		} catch (Exception e) {
			System.out.println("Caught " + e);
		}
		System.out.println("All done");
	}
}
