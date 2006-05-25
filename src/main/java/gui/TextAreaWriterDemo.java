package gui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.darwinsys.io.WriterToJTextArea;

public class WriterToJTextAreaDemo {
	
	private static PrintWriter out;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		JFrame jf = new JFrame("test");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTextArea ta = new JTextArea(20, 60);
		jf.add(ta);
		jf.pack();
		jf.setVisible(true);
		WriterToJTextArea prog = new WriterToJTextArea(ta);
		out = prog.getWriter();
		out.println("Hello");
		out.flush();
		Thread.sleep(10000);	// If main exits immediately, the output gets closed 
								// and this causes an I/O error...
		System.exit(0);
	}
}


