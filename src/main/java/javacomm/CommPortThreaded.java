import java.io.*;
import javax.comm.*;
import java.util.*;

/**
 * A quick demo of using the Java Communications Package to open a serial port.
 * This program tries to do I/O in each direction using a separate Thread.
 *
 * Java Communications is a "standard extention" and must be downloaded
 * and installed separately from the JDK before you can even compile this 
 * program.
 *
 * @author	Ian F. Darwin, ian@darwinsys.com
 */
public class CommPortThreaded extends CommPortOpen {

	public static void main(String ap[])
		throws IOException, NoSuchPortException,PortInUseException,
			UnsupportedCommOperationException {
		CommPortThreaded cp;
		try {
			cp = new CommPortThreaded();
			cp.converse();
		} catch(Exception e) {
			System.err.println("You blew it! Here's why:\n");
			e.printStackTrace();
		}
	}

	public CommPortThreaded()
		throws IOException, NoSuchPortException, PortInUseException,
			UnsupportedCommOperationException {
		super(null);
	}

	protected void converse() throws IOException {

		String resp;		// the modem response.

		new DataThread(is, System.out).start();
		new DataThread(new DataInputStream(System.in), os).start();

	}

	class DataThread extends Thread {
		DataInputStream inStream;
		PrintStream pStream;
		DataThread(DataInputStream is, PrintStream os) {
			inStream = is;
			pStream = os;
		}
		public void run() {
			byte ch = 0;
			try {
				while ((ch = (byte)inStream.read()) != -1)
					pStream.print(ch);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
	}
}
