package functional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A Unix replacement for:
 cat lines.txt | sort | uniq | wc -l
 * It even gives the same answer :-)
 */
public class UnixPipesFiltersReplacement {

	public static void main(String[] args) throws IOException {
		// tag::main[]
		BufferedReader br = null;
		long numberLines =
			(br = new BufferedReader(new FileReader("lines.txt")))
			.lines()
			.sorted()
			.distinct()
			.count();
		br.close();
		System.out.println("lines.txt" + " contains " + numberLines + " unique lines.");
		// end::main[]
	}
}
