package io;

import java.util.Date;

public class PrintfDemo {

	/**
	 * Simple demo of printf(), from Recipe 10.3
	 */
	public static void main(String[] args) {
		System.out.printf("%04d - the year of %f%n", 1951, Math.PI);
		Date time = new Date();
		String userName = "Robin";
		System.out.printf("Hello %s at %s%n", userName, time);
	}
}
