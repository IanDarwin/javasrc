package printjdk14printservice;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.darwinsys.swingui.UtilGUI;

// BEGIN main
/**
 * Show the latest incarnation of printing, PrintService, from a GUI;
 * the GUI consists only of a "Print" button, and the filename is hardcoded,
 * but it's meant to be a minimal demo...
 */
public class PrintServiceDemo extends JFrame {

	private static final long serialVersionUID = 923572304627926023L;
	
	private static final String INPUT_FILE_NAME = "/demo.txt";

	/** main program: instantiate and show. 
	 * @throws IOException */
	public static void main(String[] av) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new PrintServiceDemo("Print Demo").setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** Constructor for GUI display with pushbutton to print */
	PrintServiceDemo(String title) {
		super(title);
		System.out.println("PrintServiceDemo.PrintServiceDemo()");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		JButton b;
		add(b = new JButton("Print"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(
					"PrintServiceDemo.PrintServiceDemo...actionPerformed()");
				try {
					print(INPUT_FILE_NAME);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(
						PrintServiceDemo.this, "Error: " + e1, "Error",
						JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
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
		System.out.println("PrintServiceDemo.print(): Printing " + fileName);
		DocFlavor flavor = DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_8;
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		//aset.add(MediaSize.NA.LETTER);
		aset.add(MediaSizeName.NA_LETTER);
		//aset.add(new JobName(INPUT_FILE_NAME, null));
		PrintService[] pservices = 
			PrintServiceLookup.lookupPrintServices(flavor, aset);
		int i;
		switch(pservices.length) {
		case 0:
			System.err.println(0);
			JOptionPane.showMessageDialog(PrintServiceDemo.this,
				"Error: No PrintService Found", "Error", 
				JOptionPane.ERROR_MESSAGE);
			return;
		case 1:
			i = 0;	// Only one printer, use it.
			break;
		default:
			i = JOptionPane.showOptionDialog(this, 
				"Pick a printer", "Choice", 
				JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, 
				null, pservices, pservices[0]);
			break;
		}
		DocPrintJob pj = pservices[i].createPrintJob();
		InputStream is = getClass().getResourceAsStream(INPUT_FILE_NAME);
		if (is == null) {
			throw new NullPointerException("Input Stream is null: file not found?");
		}
		Doc doc = new SimpleDoc(is, flavor, null);
		
		pj.print(doc, aset);
	}
}
// END main
