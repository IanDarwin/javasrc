package printjdk11printjob;

import graphics.GfxDemoCanvas;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/** PrintDemoGfx -- Construct and print a GfxDemoCanvas.  
 * JDK1.1 VERSION, using the older PrintJob.
 */
public class PrintDemoGfx1_1 {

	/** Simple demo main program. */
	public static void main(String[] av) {
		final JFrame f = new JFrame("Printing Test Dummy Frame");

		// Construct the object we want to print. Contrived:
		// this object would already exist in a real program.
		final GfxDemoCanvas thing = new GfxDemoCanvas(500, 300);

		f.getContentPane().add(thing, BorderLayout.CENTER);

		JButton printButton = new JButton("Print");
		f.getContentPane().add(printButton, BorderLayout.SOUTH);

		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PrintJob pjob = Toolkit.getDefaultToolkit().getPrintJob(f,
					"Printing Test", null);

				if (pjob == null)
					return;				// user cancelled

				// Fetch the Print Graphics object
				Graphics pg = pjob.getGraphics();

				// Now (drum roll please), ask "thing" to paint itself
				// on the printer, by calling its paint() method with 
				// a Printjob Graphics instead of a Window Graphics.
				thing.paint(pg);
				pg.dispose(); // end of this page
				pjob.end();	// end of print job.
			}
		});

		f.pack();
		f.setVisible(true);
	}
}
