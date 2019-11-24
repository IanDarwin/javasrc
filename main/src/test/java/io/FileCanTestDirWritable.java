package io;

import java.io.File;
import java.io.IOException;

/** Let's see if the File object can test for writeable directories on platforms
 * (directory write implies the ability to create/remove/rename files, at least
 * on UNIX/POSIX/Linux systems).
 * @author ian
 */
public class FileCanTestDirWritable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (String path : args) {
			process(path);
		}
	}

	/**
	 * @param path
	 */
	private static void process(final String path) {
		File f = new File(path);
		System.out.printf("File asserts %s %s a DIRECTORY%n", path, f.isDirectory() ? "is" : "is not");
		System.out.printf("File claims  %s %s be written%n" , path, f.canWrite() ? "can" : "can not");
		if (f.isDirectory() && f.canWrite()) {
			try {
				File g = File.createTempFile("FileCanTestDirWritable", null, f);
				System.out.printf("... And it can; we created %s%n", g);
				g.delete();
				System.out.printf("... (We removed it...)");
			} catch (IOException e) {
				System.out.printf("... But in fact we cannot create a file there: %s%n", e);
			}
		}
		System.out.println();
	}

}
