// NOT FINISHED!!

/*
 * ============================================================================
 *                   The Apache Software License, Version 1.1
 * ============================================================================
 * 
 *    Copyright (C) 2000-2002 The Apache Software Foundation. All
 *    rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modifica-
 * tion, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of  source code must  retain the above copyright  notice,
 *    this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. The end-user documentation included with the redistribution, if any, must
 *    include  the following  acknowledgment:  "This product includes  software
 *    developed  by the  Apache Software Foundation  (http://www.apache.org/)."
 *    Alternately, this  acknowledgment may  appear in the software itself,  if
 *    and wherever such third-party acknowledgments normally appear.
 * 
 * 4. The names "Ant" and  "Apache Software Foundation"  must not be used to
 *    endorse  or promote  products derived  from this  software without  prior
 *    written permission. For written permission, please contact
 *    apache@apache.org.
 * 
 * 5. Products  derived from this software may not  be called "Apache", nor may
 *    "Apache" appear  in their name,  without prior written permission  of the
 *    Apache Software Foundation.
 * 
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS  FOR A PARTICULAR  PURPOSE ARE  DISCLAIMED.  IN NO  EVENT SHALL  THE
 * APACHE SOFTWARE  FOUNDATION  OR ITS CONTRIBUTORS  BE LIABLE FOR  ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL,  EXEMPLARY, OR CONSEQUENTIAL  DAMAGES (INCLU-
 * DING, BUT NOT LIMITED TO, PROCUREMENT  OF SUBSTITUTE GOODS OR SERVICES; LOSS
 * OF USE, DATA, OR  PROFITS; OR BUSINESS  INTERRUPTION)  HOWEVER CAUSED AND ON
 * ANY  THEORY OF LIABILITY,  WHETHER  IN CONTRACT,  STRICT LIABILITY,  OR TORT
 * (INCLUDING  NEGLIGENCE OR  OTHERWISE) ARISING IN  ANY WAY OUT OF THE  USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * This software  consists of voluntary contributions made  by many individuals
 * on behalf of the  Apache Software Foundation.  For more  information  on the 
 * Apache Software Foundation, please see <http://www.apache.org/>.
 *
 */

package com.darwinsys.ant;

import java.io.*;
import java.util.*;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import org.apache.tools.ant.taskdefs.Execute;

/**
 * An Ant Task to an external C compiler.
 * <p>
 * TODO: add more settings.
 * @author Ian Darwin
 * @version $Id$
 */
public class CCTask extends Task {
	private final static int COMPILER_GCC = 1;
	private final static int COMPILER_CC = 2;
	/** The compiler to use (gcc, cc, etc.) */
	private int compiler = COMPILER_GCC;
	/** The full path to the compiler */
	private String compilerExecutable = null;
	/** whether to print+return or throw BuildException */
	private boolean failOnError = true;

	/** Set the drive or directory name */
	public void setCompiler(String compiler) {
		if (compiler.equalsIgnoreCase("gcc"))
			this.compiler = COMPILER_GCC;
		throw new BuildException("unknown compiler: " + compiler);
	}

	/** Set the destFileName name */
	public void setDestFileName(String destFileName) {
		// this.destFileName = destFileName;
	}

	/** Set the full path to the compiler */
	public void setCompilerExecutable(String compilerExecutable) {
		this.compilerExecutable = compilerExecutable;
	}

	/** whether to print+return or throw BuildException */
	public void setFailOnError(boolean fail) {
		this.failOnError = fail;
	}

	/** Handle an error, either by throwing a BuildException or by
	 * printing the message and the Throwable
	 * @see setFailOnError(boolean);
	 */
	protected void die(String msg, Throwable t) {
		if (failOnError) {
			throw new BuildException(msg, t);
		}
		System.err.println(msg);
		t.printStackTrace(System.err);
	}

	/** Handle an error, either by throwing a BuildException or by
	 * printing the message.
	 * @see setFailOnError(boolean);
	 */
	protected void die(String msg) {
		if (failOnError) {
				throw new BuildException(msg);
		}
		System.err.println(msg);
	}

	/** We should be all set now; run the task */
	public void execute() throws BuildException {
String srcDir = null;
		if (srcDir == null) {
			die("srcDir parameter is not set");
		}
String destFile = null;
		if (destFile == null) {
			die("dstFile parameter is not set");
		}
		ArrayList command = new ArrayList();
		Execute proc = null;
		try { 
			// command.add(prog.getCanonicalPath() );
			command.add("-c");
			command.add(srcDir);
			command.add(destFile);
			System.out.println("Command is: " + command);

			proc = new Execute();
			proc.setAntRun(getProject());
			proc.setVMLauncher(true); // OK to run directly, no wildcards &c.

			String[] args = (String[])command.toArray(new String[0]);
			proc.setCommandline(args);

			// START IT RUNNING
			proc.execute();

			// Wait for it to end...
			// proc.waitFor();

		} catch (IOException ex) {
			die("Input or Output Failure", ex);
		}
		// assert(proc != null);
		int ret = proc.getExitValue();
		if (ret != 0) {
			die("Compiler returned exitStatus " + ret);
		}
		// No news is good news; ant will say "BUILD SUCCESSFUL" if no failure.
	}
}
