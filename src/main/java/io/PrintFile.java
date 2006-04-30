package io;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
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

/**
 * PrintFile -- Print a file named on the command line
 */
public class PrintFile extends Frame {

	private static final long serialVersionUID = 1370956180129342147L;
	/** The number of pages to print */
	protected static final int NPAGES = 3;
	/** The actual number of pages */
	protected int nPages;
	/** The PrintJob object */
	PrintJob pjob = null;	// refers to whole print job

	/** main program: instantiate and show. */
	public static void main(String[] av) {
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

	/** Construct a PrintFile object */
	PrintFile() {
		setLayout(new FlowLayout());
		Button b;
		add(b = new Button("Cancel"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pjob != null)	// if quit while printing!
					pjob.end();
				System.exit(0);
			}
		});
		pack();
	}

	/** Print a file by name */
	public void print(String fn) {
		// open it, call the other guy 
		FileReader ifile = null;
		try {
			ifile = new FileReader(fn);
		} catch (FileNotFoundException fnf) {
			System.err.println("File not found!");
		}
		print(ifile);
	}

	/** Print a file by File */
	public void print(Reader ifile) {
		BufferedReader is = new BufferedReader(ifile);
		Graphics g = null;	// refers to current page
		System.out.println("Doing print");
		pjob = getToolkit().getPrintJob(this,
			"Printing Test", (Properties)null);
		if (pjob == null)          // User cancelled??
			return;
		Dimension pDim = pjob.getPageDimension();
		int pRes = pjob.getPageResolution();
		System.out.println("Page size " + pDim + "; Res " + pRes);
		g = pjob.getGraphics();
		g.setColor(Color.black);
		g.setFont(new Font("SansSerif", Font.PLAIN, 12));
		int y = 100;
		String line;
		try {
			while ((line = is.readLine()) != null) {
				g.drawString(line, 10, y+=18);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		// g.drawString("Page " + pgNum, 300, 300);
		g.dispose(); // flush page
		pjob.end();	// total end of print job.
		pjob = null;	// avoid redundant calls to pjob.end()
	}
}
