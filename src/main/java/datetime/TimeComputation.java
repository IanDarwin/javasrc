import java.io.*;
import java.text.*;

import com.darwinsys.lang.SysDep;

/**
 * Timer for processing sqrt and I/O operations.
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class Timer {
	public static void main(String[] argv) {
		try {
			new Timer().run();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	public void run() throws IOException {

		DataOutputStream n = new DataOutputStream(
			new BufferedOutputStream(new FileOutputStream(SysDep.getDevNull())));
		long t0, t1;
		System.out.println("Java Starts at " + (t0=System.currentTimeMillis()));
		double k;
		for (int i=0; i<100000; i++) {
			k = 2.1 * Math.sqrt((double)i);
			n.writeDouble(k);
		}
		System.out.println("Java Ends at " + (t1=System.currentTimeMillis()));
		double deltaT = t1-t0;
		System.out.println("This run took " + 
			DecimalFormat.getInstance().format(deltaT/1000.) + " seconds.");
	}

}
