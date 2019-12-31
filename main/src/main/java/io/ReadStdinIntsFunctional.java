package io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * Read one or more integers, one per line, from the standard input.
 * @author Daniel Hinojosa
 */
public class ReadStdinIntsFunctional {
	private static Stream<Integer> parseIntSafe(String s) {
		try {
			return Stream.of(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			return Stream.empty();
		}
	}

	public static void main(String[] args) {
		BufferedReader is =
			new BufferedReader(new InputStreamReader(System.in));
		is.lines()
		  .flatMap(ReadStdinIntsFunctional::parseIntSafe)
		  .forEach(System.out::println);
	}
}
