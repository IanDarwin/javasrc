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

/** Demonstrate use of ThreadLocal, a mechanism that provides per-thread copies of
 * some reference, to facilitate thread-safe code.
 * ThreadLocal is a standard Java class.
 * There are two basic ways of putting the value into a ThreadLocal:
 * <ol>
 * 	<li>Subclass ThreadLocal and have initialValue() generate the value;
 * 	<li>Instantiate ThreadLocal and use put() to put the value in.
 * </ol>
 * Both methods are good.
 */
public class ThreadLocalDemo extends Thread {

	ThreadLocalDemo(String name) {
		super.setName(name);
	}

	/** This ThreadLocal holds the Client reference for each Thread.
	 * Make ThreadLocal instance static, to show that it is not an instance variable
	 * but is derived from Thread.currentThead() regardless of static/instance access
	 */
	private static ThreadLocal<Client> myClient = new ThreadLocal<Client>() {
		// initialValue() is called magically when you first call get().
		@Override
		protected synchronized Client initialValue() {
			return new Client();
		}
	};
	@Override
	public void run() {
		System.out.println("Thread " + Thread.currentThread().getName() +
			" sees client " + myClient.get());
	}

	public static void main(String[] args) {
		Thread t1 = new ThreadLocalDemo("demo 1");
		t1.start();
		Thread t2 = new ThreadLocalDemo("demo 2");
		t2.start();
		Thread.yield();
		System.out.println("Main program sees client " + myClient.get());
	}

	/** Simple data class, in real life clients would have more fields! */
	private static class Client {
		/** A serial number for clients */
		private static int clientNum = 0;
		/** This Client instance's serial number */
		private int clNum;

		Client() {
			clNum = clientNum++;
		}

		public String toString() {
			return "Client[" + clNum + "]";
		}
	}
}
