package com.darwinsys.ant;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Execute;

/**
 * An Ant Task to fetch an HTTP URL into a file.
 * <p>
 * TODO: add mode settings.
 * @author Ian Darwin
 * @version $Id$
 */
public class HttpTask extends Task {
	/** The URL */
	private String theURL;
	/** The output file (null == System.out) */
	private String outputFileName;
	/** whether to print+return or throw BuildException */
	private boolean failOnError = true;

	/** Set the output file */
	public void setOutputFile(String outfile) {
		outputFileName = outfile;
	}

	/** Set the URL */
	public void setURL(String URL) {
		theURL = URL;
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
		if (theURL == null) {
			die("URL parameter is not set");
		}
		URL u = null;
		try {
			u = new URL(theURL);
			BufferedReader is = new BufferedReader(
				new InputStreamReader(u.openStream()));
			PrintWriter pw = (outputFileName == null) ?
				new PrintWriter(System.out) :
				new PrintWriter(new FileWriter(outputFileName));
			String line;
			while ((line = is.readLine()) != null) {
				pw.println(line);
			}
			is.close();
			if (outputFileName != null) {
				pw.close();
			}
		} catch (MalformedURLException ex) {
			die("URL Syntax error", ex);
		} catch (FileNotFoundException ex) {
			die("Can't open file " + outputFileName);
		} catch (IOException ex) {
			die("Input or output error ", ex);
		}
		// No news is good news; ant will say "BUILD SUCCESSFUL" if no failure.
	}
}
