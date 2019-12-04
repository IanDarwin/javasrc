package gui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.darwinsys.io.TextAreaWriter;

public class TextAreaWriterDemo {
	
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
		PrintWriter out = new PrintWriter(new TextAreaWriter(ta));
		out.println("Hello");
		out.print("Here is a boolean: ");
		out.println(false);
		out.print("... and a double: ");
		out.println(Math.PI);
		out.flush();
		out.close();
	}
}


