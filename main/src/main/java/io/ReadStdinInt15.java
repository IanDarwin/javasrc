package io;

import java.util.Scanner;
/**
 * Read an int from Standard Input, using 1.5
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class ReadStdinInt15 {
	public static void main(String[] ap) {
		int val;
		Scanner sc = new Scanner(System.in);      // Requires Java 5
		val = sc.nextInt();
		System.out.println("I read this number: " + val);
		sc.close();
	}
}
