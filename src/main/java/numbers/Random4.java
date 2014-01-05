package numbers;

import java.util.*;
import java.io.*;

/** Print "n" calls to nextDouble() and nextGaussian() in raw form
 * using java.util.Random.next*(); results can be plotted (for example,
 * using the "R" script randomnesshistograms.r).
 */
public class Random4 {
	private static final int N = 10000;
	public static void main(String[] argv) throws IOException {
		Random r = new Random();
		try (PrintWriter file1 = new PrintWriter(new FileWriter("file1"))) {
			try (PrintWriter file2 = new PrintWriter(new FileWriter("file2"))) {
				for (int i=0; i<N; i++) {
					file1.println(r.nextDouble());
					file2.println(r.nextGaussian());
				}
			}
		}
	}
}
