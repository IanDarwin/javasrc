import java.awt.*;
import java.util.*;

/**
 * PrintDemoGfx -- Construct a GfxDemoCanvas and print it.
 */
public class PrintDemoGfx {
	public static void main(String av[]) {
		Frame f = new Frame("Printing Test Dummy Frame");

		f.setVisible(true);
		PrintJob pjob = Toolkit.getDefaultToolkit().getPrintJob(f,
			"Printing Test", (Properties)null);
		if (pjob == null)
			return;				// user cancelled

		// Fetch the Print Graphics object
		Graphics pg = pjob.getGraphics();

		// Construct the object we want to print. Contrived:
		// this object would already exist in a real program.
		GfxDemoCanvas thing = new GfxDemoCanvas(400, 300);

		// Now (drum roll please), ask "thing" to paint itself
		// on the printer, by calling its paint() method with 
		// a Printjob Graphics instead of a Window Graphics.
		thing.paint(pg);

		pg.dispose(); // end of this page
		pjob.end();	// end of print job.
	}
}
