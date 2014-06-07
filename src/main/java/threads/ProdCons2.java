package threads;

/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
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
import java.util.LinkedList;

/** Producer-consumer in Java, Take II.
 */
// BEGIN main
public class ProdCons2 {

	/** Throughout the code, this is the object we synchronize on so this
	 * is also the object we wait() and notifyAll() on.
	 */
	protected LinkedList<Object> list = new LinkedList<>();
	protected int MAX = 10;
	protected boolean done = false; // Also protected by lock on list.

	/** Inner class representing the Producer side */
	class Producer extends Thread {

		public void run() {
			while (true) {
				Object justProduced = getRequestFromNetwork();
				// Get request from the network - outside the synch section.
				// We're simulating this actually reading from a client, and it
				// might have to wait for hours if the client is having coffee.
				synchronized(list) {
					while (list.size() == MAX) { // queue "full"
						try {
							System.out.println("Producer WAITING");
							list.wait();	 // Limit the size
						} catch (InterruptedException ex) {
							System.out.println("Producer INTERRUPTED");
						}
					}
					list.addFirst(justProduced);
					list.notifyAll();	// must own the lock
					System.out.println("Produced 1; List size now " + list.size());
					if (done)
						break;
					// yield();	// Useful for green threads & demo programs.
				}
			}
		}

		Object getRequestFromNetwork() {	// Simulation of reading from client
			// try {
			// 	Thread.sleep(10); // simulate time passing during read
			// } catch (InterruptedException ex) {
			// 	System.out.println("Producer Read INTERRUPTED");
			// }
			return(new Object());
		}
	}

	/** Inner class representing the Consumer side */
	class Consumer extends Thread {
		public void run() {
			while (true) {
				Object obj = null;
				synchronized(list) {
					while (list.size() == 0) {
						try {
							System.out.println("CONSUMER WAITING");
							list.wait();	// must own the lock
						} catch (InterruptedException ex) {
							System.out.println("CONSUMER INTERRUPTED");
						}
					}
					obj = list.removeLast();
					list.notifyAll();
					int len = list.size();
					System.out.println("List size now " + len);
					if (done)
						break;
				}
				process(obj);	// Outside synch section (could take time)
				//yield(); DITTO
			}
		}

		void process(Object obj) {
			// Thread.sleep(1234) // Simulate time passing
			System.out.println("Consuming object " + obj);
		}
	}

	ProdCons2(int nP, int nC) {
		for (int i=0; i<nP; i++)
			new Producer().start();
		for (int i=0; i<nC; i++)
			new Consumer().start();
	}

	public static void main(String[] args)
	throws IOException, InterruptedException {

		// Start producers and consumers
		int numProducers = 4;
		int numConsumers = 3;
		ProdCons2 pc = new ProdCons2(numProducers, numConsumers);

		// Let it run for, say, 10 seconds
		Thread.sleep(10*1000);

		// End of simulation - shut down gracefully
		synchronized(pc.list) {
			pc.done = true;
			pc.list.notifyAll();
		}
	}
}
// END main
