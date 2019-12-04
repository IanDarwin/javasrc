package printjdk11printjob;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JFrame;

/**
 * PrintDemo1 -- Print Demo Program - JDK1.1 Version
 *      Prints three pages (or so).
 */
public class PrintDemo1 extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6995544371446195979L;
	/** The number of pages to print */
	protected static final int NPAGES = 3;
	/** The actual number of pages */
	protected int nPages;
	/** The PrintJob object */
	PrintJob pjob = null;	// refers to whole print job

	/** main program: instantiate and show. */
	public static void main(String[] av) {
		PrintDemo1 p;
		if (av.length>0)
			p = new PrintDemo1(Integer.parseInt(av[0]));
		else
			p = new PrintDemo1();
		p.setVisible(true);
	}

	/** Construct a PrintDemo1 with given number of pages */
	PrintDemo1(int i) {
		nPages = i;
		setLayout(new FlowLayout());
		Button b;
		add(b = new Button("Print"));
		b.addActionListener(this);
		add(b = new Button("Quit"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pjob != null)	// if quit while printing!
					pjob.end();
				System.exit(0);
			}
		});
		pack();
	}
	/** Construct a PrintDemo1 with default values */
	PrintDemo1() {
		this(NPAGES);
	}

	/** Action handler does the work of the printing test. */
	public void actionPerformed(ActionEvent evt) {
		Graphics pg = null;	// refers to current page
		System.out.println("Doing print");
		pjob = getToolkit().getPrintJob(this,
			"Printing Test", (Properties)null);
		if (pjob == null)          // User cancelled??
			return;
		Dimension pDim = pjob.getPageDimension();
		int pRes = pjob.getPageResolution();
		System.out.println("Page size " + pDim + "; Res " + pRes);
		// Print up to NPAGES
		for (int pgNum=0; pgNum<nPages; pgNum++) {
			System.out.println("Starting page # " + pgNum);
			pg = pjob.getGraphics();
			if (pg == null) 	// ??
				return;
			// setColor needed on Solaris (a bug!?)
			pg.setColor(Color.black);
			pg.setFont(new Font("Helvetica", Font.PLAIN, 48));
			pg.drawString("Hello World", 200, 200);
			pg.drawString("Page " + pgNum, 300, 300);
			System.out.println("Disposing of page " + pgNum);
			pg.dispose(); // flush page
			System.out.println("Disposed of page " + pgNum);
		}
		pjob.end();	// total end of print job.
		pjob = null;	// avoid redundant calls to pjob.end()
	}
}
