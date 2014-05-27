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

// import - none

/** Demonstration of using a Thread to automatically save
 * the user's work periodically.
 */
// BEGIN main
public class AutoSave extends Thread {
	/** The FileSave interface is implemented by the main class. */
	protected FileSaver model;
	/** How long to sleep between tries */
	public static final int MINUTES = 5;
	private static final int SECONDS = MINUTES * 60;

	public AutoSave(FileSaver m) {
		super("AutoSave Thread");
		setDaemon(true);		// so we don't keep the main app alive
		model = m;
	}

	public void run() {
		while (true) {		// entire run method runs forever.
			try {
				sleep(SECONDS*1000);
			} catch (InterruptedException e) {
				// do nothing with it
			}
			if (model.wantAutoSave() && model.hasUnsavedChanges())
				model.saveFile(null);
		}
	}

	// Not shown:
	// 1) saveFile() must now be synchronized.
	// 2) method that shuts down main program be synchronized on *SAME* object
}

/** Local copy of FileSaver interface, for compiling AutoSave demo. */
interface FileSaver {
	/** Load new model from fn; if null, prompt for new fname */
	public void loadFile(String fn);

	/** Ask the model if it wants AutoSave done for it */
	public boolean wantAutoSave();

	/** Ask the model if it has any unsaved changes, don't save otherwise */
	public boolean hasUnsavedChanges();

	/** Save the current model's data in fn. 
	 * If fn == null, use current fname or prompt for a filename if null.
	 */
	public void saveFile(String fn);
}
// END main
