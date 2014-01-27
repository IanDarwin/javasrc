package datetimeold;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import com.darwinsys.lang.SysDep;

/**
 * TimeComputation for processing sqrt and I/O operations.
 * Does NOT include time to open or close the file.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public class TimeComputation {
	public static void main(String[] argv) {
		try {
			new TimeComputation().run();
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
		n.close();
	}

}
