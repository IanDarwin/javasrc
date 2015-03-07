package io.lookup;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 * MakeList makes the properties list for the Lookup program.
 *
 * @author	YOUR NAME HERE
 */
public class HINTS /*rename to MakeList*/ {
	/** The output file name. */
	public static final String FILENAME = "ClassDoc.properties";

	/** The output file */
	protected PrintWriter file;

	/** The system Properties list */
	protected static Properties sysProps = System.getProperties();

	/** Simple main program, construct self, process each .ZIP file
	 * found in CLASSPATH.
	 */
	public static void main(String[] argv) {
		MakeList maker = new MakeList();

		maker.processOneZip("/jdk1.2/jre/lib/rt.jar");

		System.err.println("All done!");
	}

	/** Construct a MakeList */
	public HINTS() {
		try {
			file = null;// XXX Make a PrintWriter for FILENAME
		} catch (Exception e) {
			System.err.println("ERROR: " + e);
			System.exit(1);
		}
	}

	/** For each Zip file, for each entry, xref it */
	public void processOneZip(String fileName) {
		try {
			@SuppressWarnings("unused")
			ZipFile zippy = null;// XXX Make a ZipFile for 
					// the file named in "fileName"
		
			@SuppressWarnings({ "unused", "rawtypes" })
			Enumeration all = null;// XXX Get the list from "zippy"
			while (true/* XXX More elements in the list*/) {
				// XXX get the next element from the enumeration
				// cast it to a ZipEntry
				// pass it on to doClass
			}
		} catch (Exception err) {
			System.err.println("IO Error: " + err);
			return;
		}
		//XXXfile.flush();	// N.B. to complete the output
	}

	/** Format the fields and methods of one class, given its name.
	 */
	@SuppressWarnings("unused")
	protected void doClass(String zipName) {

		// Step 1: get this much working.
		System.out.println(zipName);

		String longName;
		String shortName;

		// XXX Step 2: do the String manipulation to turn
		//	java/lang/Object.class
		// into
		//	object
		// and
		//	java.lang.Object
		// then println(shortName + "=" + longName);
	}
}
