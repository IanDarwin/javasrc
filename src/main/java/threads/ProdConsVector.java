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

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/** Producer-Consumer in Java, implemented based on
 * Vector synchronization and burning CPU instead of
 * doing "proper" wait/notify synchronization.
 * DO NOT BASE REAL CODE ON THIS.
 */
public class ProdConsVector {

	private List<Object> list = new Vector<Object>();
	
	private boolean done = false;

	/** Inner class representing the Producer */
	class Producer extends Thread {
		public void run() {
			while (!done) {
				Object justProduced = new Object();
				list.add(justProduced);
				System.out.println(
						"Added " + justProduced + ", list size " + list.size());
				try {
					sleep(napTime());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** Inner class representing the Consumer side */
	class Consumer extends Thread {
		public void run() {
			Object obj = null;
			while (!done) {
				if (list.size() == 0) {
					try {
						sleep(napTime());
					} catch (InterruptedException ex) {
						return;
					}
				} else {
					obj = list.get(0);				
					System.out.println(
						"Consuming " + obj + "; list size " + list.size());
				}
			}
		}
	}

	ProdConsVector(int nP, int nC) {
		for (int i=0; i<nP; i++)
			new Producer().start();
		for (int i=0; i<nC; i++)
			new Consumer().start();
	}
	
	private Random random = new Random();
	
	int napTime() {
		return random.nextInt(100);
	}

	public static void main(String[] args)
	throws IOException, InterruptedException {

		// Start producers and consumers
		int numProducers = 4;
		int numConsumers = 3;
		ProdConsVector pc = new ProdConsVector(numProducers, numConsumers);

		// Let it run for, say, 10 seconds
		Thread.sleep(10*1000);
		
		pc.done = true;

		System.exit(0);
	}
}
