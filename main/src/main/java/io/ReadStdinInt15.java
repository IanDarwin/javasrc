package io;

import java.util.Scanner;

/**
 * Read an int from Standard Input, using 1.5 Scanner class
 * @author	Ian F. Darwin, https://darwinsys.com/
 */
public class ReadStdinInt15 {
	public static void main(String[] ap) {
		int val;
		Scanner sc = new Scanner(System.in);
		val = sc.nextInt();
		System.out.println("I read this number: " + val);
		sc.close();
	}
}
