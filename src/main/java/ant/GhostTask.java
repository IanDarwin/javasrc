package com.darwinsys.ant;

import java.io.*;
import java.util.*;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import org.apache.tools.ant.taskdefs.Execute;

/**
 * An Ant Task to run Symantec Ghost in disk-to-image mode
 */
public class GhostTask extends Task {
	/** The source drive/directory */
	private String srcDir;
	/** The target drive/directory */
	private String destFile;
	/** Ghost home directory */
	private String ghostHome = "C:\\program files\\symantec\\ghost";
	/** Ghost program, version, extension, ... */
	private String ghostProg = "ghost.exe";
	/** whether to print+return or throw BuildException */
	boolean failOnError = true;

	/** Set the drive or directory name */
	public void setSrcDir(String srcDir) {
		this.srcDir = srcDir;
	}

	/** Set the destFile file name */
	public void setDestFile(String destFile) {
		this.destFile = destFile;
	}

	/** Set the Ghost home directory */
	public void setGhostHome(String GhostHome) {
		this.ghostHome = GhostHome;
	}

	/** Set the Ghost home directory */
	public void setGhostProg(String GhostProg) {
		this.ghostProg = GhostProg;
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
		if (srcDir == null)
			die("src parameter is not set");
		if (destFile == null)
			die("dstFile parameter is not set");
		System.out.println("Ghost is: " + ghostHome + "\\" + "ghost.exe");
		File d = new File(ghostHome);
		if (!d.isDirectory()) {
			die("Ghost home " + ghostHome + " does not exist.");
		}
		if (!d.isDirectory()) {
			die("Ghost home " + ghostHome + " does not contain " + ghostProg);
		}
		File prog = new File(d, "ghost.exe");
		ArrayList command = new ArrayList();
		Execute proc = null;
		try { 
			command.add(prog.getCanonicalPath() );
			command.add("/d");
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

			// Don't need to wait for it to end...
			// proc.waitFor();

		} catch (IOException ex) {
			die("Input or Output Failure", ex);
		}
		// assert(proc != null);
		int ret = proc.getExitValue();
		if (ret != 0) {
			die("Ghost program returned exitStatus " + ret);
		}
		// No news is good news; ant will say "BUILD SUCCESSFUL" if no failure.
	}
}

