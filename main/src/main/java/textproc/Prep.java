package textproc;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/** 
 * prep - output text file(s) one word per line, in "prep"aration
 * for use in text processing scripts.
 * Ancient unix magic but still works.
 * Earlier versions were used as an example in various Bell Labs
 * talks and papers; I made this sh version in the mid-1980's
 * 
 * cat $* |
 * 	tr A-Z a-z | tr -cd 'a-z \n' | tr -s ' ' '\n'
 *
 * The last step doesn't work on gnu tr but does on BSD.
 *
 * Re-implemented in Java in 2024, just for speed.
 * Ideally use graalvm's "nativeimage" to make it fastest.
 */
public class Prep {
	public static void main(String[] files) throws IOException {
		if (files.length == 0) {
			process(System.in);
		} else {
			for (String f : files) {
				process(new FileInputStream(f));
			}
		}
	}

	static void process(InputStream ins) throws IOException {
		int ich;
		while ((ich = ins.read()) != -1) {
			if (ich > 255)
				continue;
			char c = (char)ich;
			if (Character.isAlphabetic(c))
				send(c);
			else
				send('\n');
		}
	}

	static int old = 0;
	static void send(char ch) {
		if (old == '\n' && ch == '\n')
			return;
		System.out.print(ch);
		old = ch;
	}
}
