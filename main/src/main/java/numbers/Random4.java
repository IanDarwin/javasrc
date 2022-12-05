package numbers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/** Print "n" calls to nextDouble() and nextGaussian() in raw form
 * using java.util.Random.next*(); results can be plotted (for example,
 * using the "R" script randomnesshistograms.r).
 */
public class Random4 {
	private static int N = 10000;
	public static void main(String[] argv) throws IOException {
		if (argv.length == 1) {
			try {
				N = Integer.parseInt(argv[0]);
			} catch (NumberFormatException ex) {
				System.out.printf("Number %s invalid, using %d\n", argv[0], N);
			}
		}
		System.out.println("Printing " + N + " randoms to each text file");
		Random r = new Random();
		try (PrintWriter file1 = new PrintWriter(new FileWriter("normal.txt"))) {
			try (PrintWriter file2 = new PrintWriter(new FileWriter("gaussian.txt"))) {
				for (int i=0; i<N; i++) {
					file1.println(r.nextDouble());
					file2.println(r.nextGaussian());
				}
			}
		}
	}
}
