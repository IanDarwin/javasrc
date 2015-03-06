package gui;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ProgressMonitorInputStream;

/**
 * Demonstrate ProgressMeterInputStream.
 *
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class ProgressMeterStrmDemo extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	public void readTheFile() throws IOException {
		// OK, we're going to read a file. Do it...
		try (BufferedReader ds = new BufferedReader(
				new InputStreamReader(
						new ProgressMonitorInputStream(this,
								"Loading...", new FileInputStream("index.htm")))); ){

			// Now read it...
			String line;
			while ((line = ds.readLine()) != null) {
				if (System.getProperties().getProperty("debug.lines")!=null)
					System.err.println("Read this line: " + line);
				try {
					Thread.sleep(200);		// slow it down a bit.
				} catch(InterruptedException e) {
					return;
				}
			}
		}
	}

	/** We use a separate "thread" (see Threads chapter) to do the reading,
	 * so the GUI can run independantly (since we have "sleep" calls to make
	 * it appear to run more slowly).
	 */
	public void run() {
		try {
			readTheFile();
		} catch (EOFException nme) {
			return;
		} catch (IOException e) {
			System.err.println(e.toString());
			return;
		}
	}

	public ProgressMeterStrmDemo() {
		new Thread(this).start();
	}

	public static void main(String[] av) {
		ProgressMeterStrmDemo demo = new ProgressMeterStrmDemo();
		demo.setSize(100, 100);
		demo.getContentPane().add(new JLabel("ProgressMeterStrmDemo"));
		demo.pack();
		demo.setVisible(true);

	} 
}
