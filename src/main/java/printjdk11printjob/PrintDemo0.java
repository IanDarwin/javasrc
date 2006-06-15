package printjdk11printjob;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.util.Properties;

import javax.swing.JFrame;

/**
 * PrintDemo0 -- Print Demo Program - JDK1.1 Version
 *      Prints three pages (or so).
 */
public class PrintDemo0 extends JFrame {
	
	private static final long serialVersionUID = 3265356820912615227L;
	protected static final int NPAGES = 3;

	/** main program: instantiate and run. */
	public static void main(String[] av) {
		PrintDemo0 pd = new PrintDemo0();
		pd.setVisible(true);
		pd.print(NPAGES);
	}

	public void print(int nPages) {
		PrintJob pjob = null;	// refers to whole print job
		Graphics pg = null;	// refers to current page

		System.out.println("Getting PrintJob");
		pjob = getToolkit().getPrintJob(this,
			"Printing Test", (Properties)null);
		if (pjob == null)          // User cancelled??
			return;

		// Just get and show dimensions; should use in x,y calcs.
		Dimension pDim = pjob.getPageDimension();
		int pRes = pjob.getPageResolution();
		System.out.println("Page size " + pDim + "; Res " + pRes);

		// Print up to "np" number of pages
		for (int pgNum=1; pgNum<=nPages; pgNum++) {
			System.out.println("Starting page # " + pgNum);
			pg = pjob.getGraphics();
			if (pg == null) 	// ??
				return;
			pg.setColor(Color.black);
			pg.setFont(new Font("Times", Font.PLAIN, 12));
			pg.drawString("Hello World", 100, 100);
			pg.drawString("Page" + pgNum, 300, 300);
			pg.dispose(); // flush page
			System.out.println("All done with page " + pgNum);
		}
		pjob.end();	// total end of print job.
		pjob = null;	// avoid redundant calls to pjob.end()
		return;
	}
}
