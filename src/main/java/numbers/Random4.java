package numbers;

import java.util.*;
import java.io.*;

/** Print "n" calls to nextDouble() and nextGaussian() in raw form
 * using java.util.Random.next*(); results can be plotted.
 */
public class Random4 {
	public static void main(String[] argv) throws IOException {
		// java.util.Random methods are non-static, do need to construct Math
		Random r = new Random();
		PrintWriter file1 = new PrintWriter(new FileWriter("file1"));
		PrintWriter file2 = new PrintWriter(new FileWriter("file2"));
		for (int i=0; i<10000; i++) {
			file1.println(r.nextDouble());
			file2.println(r.nextGaussian());
		}
		file1.close();
		file2.close();
	}
}
