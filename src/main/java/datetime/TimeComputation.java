import java.io.*;
/**
 * Timer for standalone, line-mode application.
 */
public class Timer {
	public static void main(String argv[]) {
		try {
			new Timer().run();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	public void run() throws IOException {
		DataOutputStream n = new DataOutputStream(
			new BufferedOutputStream(new FileOutputStream("nul:")));
		long t0, t1;
		System.out.println("Java Starts at " + (t0=System.currentTimeMillis()));
		double k;
		for (int i=0; i<1000000; i++) {
			k = 2.1 * Math.sqrt((double)i);
			n.writeDouble(k);
		}
		System.out.println("Java Ends at " + (t1=System.currentTimeMillis()));
		System.out.println("Time=" + (t1-t0));
	}
}
