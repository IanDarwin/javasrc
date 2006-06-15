package printjdk11printjob;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * PrintFile -- Print a file named on the command line
 */
public class PrintFile extends JFrame {

	private static final long serialVersionUID = -668723004626260238L;
	/** The number of pages to print */
	protected static final int NPAGES = 3;
	/** The actual number of pages */
	protected int nPages;
	/** The PrintJob object */
	PrintJob pjob = null;	// refers to whole print job

	/** main program: instantiate and show. 
	 * @throws IOException */
	public static void main(String[] av) throws IOException {
		PrintFile p = new PrintFile();
		p.setVisible(true);
		if (av.length==0)
			p.print(new InputStreamReader(System.in));
		else
			for (int i=0; i<av.length; i++)
				p.print(av[i]);
		p.setVisible(false);
		p.dispose();
		System.exit(0);
	}

	/** Constructor */
	PrintFile() {
		setLayout(new FlowLayout());
		JButton b;
		add(b = new JButton("Cancel"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pjob != null)	// if quit while printing!
					pjob.end();
				System.exit(0);
			}
		});
		pack();
	}

	/** Print a file by name 
	 * @throws IOException
	 */
	public void print(String fn) throws IOException {
		// open it, call the other guy 
		FileReader ifile = null;
		try {
			ifile = new FileReader(fn);
		} catch (FileNotFoundException fnf) {
			System.err.println("File not found!");
		}
		print(ifile);
	}

	/** Print a file by File 
	 * @throws IOException */
	public void print(Reader ifile) throws IOException {
		BufferedReader is = new BufferedReader(ifile);
		System.out.println("Doing print");
		pjob = getToolkit().getPrintJob(this,
			"Printing Test", (Properties)null);
		if (pjob == null)          // User cancelled??
			return;
		Dimension pDim = pjob.getPageDimension();
		int pRes = pjob.getPageResolution();
		System.out.println("Page size " + pDim + "; Res " + pRes);
		Graphics g = pjob.getGraphics();
		if (g == null) {
			System.err.println("Err, Graphics object from PrintJob is null!");
			return;
		}
		g.setColor(Color.black);
		g.setFont(new Font("SansSerif", Font.PLAIN, 12));
		int y = 100;
		String line;
		while ((line = is.readLine()) != null) {
			g.drawString(line, 10, y+=18);
		}
		// g.drawString("Page " + pgNum, 300, 300);
		g.dispose(); // flush page
		pjob.end();	// total end of print job.
		pjob = null;	// avoid redundant calls to pjob.end()
	}
}
