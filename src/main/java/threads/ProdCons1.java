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

/** Producer-Consumer in Java. Version 1.
 */
// BEGIN main
public class ProdCons1 {

	protected LinkedList<Object> list = new LinkedList<>();

	protected void produce() {
		int len = 0;
		synchronized(list) {
			Object justProduced = new Object();
			list.addFirst(justProduced);
			len = list.size();
			list.notifyAll();
		}
		System.out.println("List size now " + len);
	}

	protected void consume() {
		Object obj = null;
		int len = 0;
		synchronized(list) {
			while (list.size() == 0) {
				try {
					list.wait();
				} catch (InterruptedException ex) {
					return;
				}
			}
			obj = list.removeLast();
			len = list.size();
		}
		System.out.println("Consuming object " + obj);
		System.out.println("List size now " + len);
	}

	public static void main(String[] args) throws IOException {
		ProdCons1 pc = new ProdCons1();
		System.out.println("Ready (p to produce, c to consume):");
		int i;
		while ((i = System.in.read()) != -1) {
			char ch = (char)i;
			switch(ch) {
				case 'p':	pc.produce(); break;
				case 'c':	pc.consume(); break;
			}
		}
	}
}
// END main
