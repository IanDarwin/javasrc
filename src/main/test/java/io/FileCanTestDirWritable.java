package io;

import java.io.File;

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
		System.out.printf("File object is %s%n", f);
		System.out.printf("File %s a DIRECTORY%n", f.isDirectory() ? "is" : "is not");
		System.out.printf("File %s be written%n", f.canWrite() ? "can" : "can not");
		System.out.println();
	}

}
