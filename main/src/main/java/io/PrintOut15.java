package io;

import java.io.*;

/**
 * As of Java 5 you can construct a PrintWriter with just a file name...
 */
public class PrintOut15 {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("funny.txt");
		out.println("Hello");
		out.close();
	}
}
