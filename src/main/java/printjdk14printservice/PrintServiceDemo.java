package printjdk14printservice;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.darwinsys.swingui.UtilGUI;

/**
 * Show the PrintService, a more high-level API than PrintJob, from a GUI;
 * the GUI consists only of a "Print" button, and the filename is hardcoded,
 * but it's meant to be a minimal demo...
 */
public class PrintServiceDemo extends JFrame {

	private static final long serialVersionUID = 923572304627926023L;
	
	private static final String INPUT_FILE_NAME = "duke.gif";

	/** main program: instantiate and show. 
	 * @throws IOException */
	public static void main(String[] av) throws IOException {
		new PrintServiceDemo("Print Demo").setVisible(true);
	}

	/** Constructor */
	PrintServiceDemo(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		JButton b;
		add(b = new JButton("Print"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					print(INPUT_FILE_NAME);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(PrintServiceDemo.this, "Error: " + e1, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		pack();
		UtilGUI.center(this);
	}

	/** Print a file by name 
	 * @throws IOException
	 * @throws PrintException 
	 */
	public void print(String fileName) throws IOException, PrintException {
	
		System.out.println("Printing " + fileName);
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.POSTSCRIPT;
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		//aset.add(MediaSizeName.NA_LETTER);
		PrintService[] pservices = PrintServiceLookup.lookupPrintServices(
				flavor, aset);
		int i;
		switch(pservices.length) {
		case 0:
			JOptionPane.showMessageDialog(PrintServiceDemo.this,
					"Error: No PrintService Found", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		case 1:
			i = 1;
			break;
		default:
			i = JOptionPane.showOptionDialog(this, "Pick a printer", "Choice", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, pservices, pservices[0]);
			break;
		}
		DocPrintJob pj = pservices[i].createPrintJob();
		Doc doc = new MyDemoDoc(INPUT_FILE_NAME, flavor);

		pj.print(doc, aset);

	}
	
	/**
	 * Simple holder for filename and document flavor.
	 */
	final class MyDemoDoc implements Doc {
		
		private DocFlavor flavor;
		private String fileName;

		public MyDemoDoc(String fileName, DocFlavor flavor) {
			this.fileName = fileName;
			this.flavor = flavor;
		}

		public DocFlavor getDocFlavor() {
			return flavor;
		}

		public Object getPrintData() throws IOException {
			return null;
		}

		public DocAttributeSet getAttributes() {
			return null;
		}

		public Reader getReaderForText() throws IOException {
			return null;
		}

		public InputStream getStreamForBytes() throws IOException {
			return getClass().getResourceAsStream(fileName);
		}
	}
}
