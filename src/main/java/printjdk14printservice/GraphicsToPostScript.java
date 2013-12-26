package printjdk14printservice;

import graphics.GfxDemoCanvas;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.print.StreamPrintService;
import javax.print.StreamPrintServiceFactory;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

/**
 * Use the JDK 1.4+ PrintStreamservice to convert graphics to PostScript(tm);
 * a sort of hybrid, uses the 1.2 PrinterJob with Print Services.
 */
public class GraphicsToPostScript {
	
	final static GfxDemoCanvas thing = new GfxDemoCanvas(400, 300);
	
	public static void main(String[] args) throws Exception {
		String psMimeType = "application/postscript";
		FileOutputStream output;
		StreamPrintServiceFactory[] factories = PrinterJob
				.lookupStreamPrintServices(psMimeType);
		if (factories.length == 0) {
			throw new IOException("Couldn't find print service");
		}
		output = new FileOutputStream("temp.ps");
		StreamPrintService psPrinter = factories[0].getPrintService(output);
		// psPrinter can now be set as the service on a PrinterJob 
		PrinterJob pjob = PrinterJob.getPrinterJob();
		pjob.setPrintService(psPrinter); // if app wants to specify this printer.
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(new Copies(2));
		
		// Tell the print system how to print our pages.
		pjob.setPrintable(new Printable() {
			/** called from the printer system to print each page */
			public int print(Graphics pg, PageFormat pf, int pageNum) {
				if (pageNum>0)		// we only print one page
					return Printable.NO_SUCH_PAGE;	// ie., end of job

				// Now just ask "thing" to paint itself
				// on the printer, by calling its paint() method with 
				// a Printjob Graphics instead of a Window Graphics.
				thing.paint(pg);

				// Tell print system that the page is ready to print
				return Printable.PAGE_EXISTS;
			}
		});

		
		pjob.print(aset);
		
		output.close();
	}

}
