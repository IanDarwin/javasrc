/* status UNFINISHED UNTESTED */
/*
 * Copyright (c) Ian F. Darwin, ian@darwinsys.com, 1996-2002.
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

import java.util.*;

/**
 * First stab at a ThreadPool
 */
public class ThreadPool {
	protected ArrayList readyPool = new ArrayList();

	protected int min;
	protected int max;

	public ThreadPool(int min, int max) {
		this.min = min;
		this.max = max;
		for (int i=1; i<=max; i++) {
			readyPool.add(new Runner());
		}
	}

	class Runner extends Thread {
		protected Runnable myTarget;

		Runner() {
			// set condition
			// start();
		}

		void setRunnable(Runnable r) {
			myTarget = r;
		}

		public void run() {
			do {
				// If we don't have a target, go back to sleep
				// Must implement notifyAll consumer relationship...
				// Of course that's the hard part...
			} while(true);
		}
	}

	/** Take a thread off the ready pool, assign it a Runnable,
	 * start it running. When it finishes, put the Thread
	 * back in the ready pool.
	 */
	public void runRunnable(Runnable r) {
		Runner runner = null;
		synchronized(readyPool) {
			runner = (Runner)readyPool.get(0);
			readyPool.remove(0);
		}
		runner.setRunnable(r);
		runner.resume();
		// ...
		readyPool.add(runner);
	}

	public static void main(String[] args) {
		ThreadPool tp = new ThreadPool(10, 15);
		for (int i = 0; i< 5; i++) {
			Runnable r = new Runnable() {
				public void run() {
					System.out.println("tick");
				}
			};
			tp.runRunnable(r);
		}
	}
}
