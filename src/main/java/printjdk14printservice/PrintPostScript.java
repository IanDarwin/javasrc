package printjdk14printservice;

import java.io.IOException;
import java.io.FileOutputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.SimpleDoc;
import javax.print.StreamPrintService;
import javax.print.StreamPrintServiceFactory;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;

/** Demonstrate finding a PrintService and printing to it */
public class PrintPostScript {
	
	public static void main(String[] args) throws IOException, PrintException {
		new PrintPostScript().print();
	}
	
	public void print() throws IOException, PrintException {
		
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.POSTSCRIPT;
		StreamPrintServiceFactory[] psfactories =
			StreamPrintServiceFactory.lookupStreamPrintServiceFactories(
				DocFlavor.SERVICE_FORMATTED.PRINTABLE,
				flavor.getMimeType());

		StreamPrintService printService = 
			psfactories[0].getPrintService(new FileOutputStream("demo.ps"));
		PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
		attrs.add(OrientationRequested.LANDSCAPE);
		attrs.add(MediaSizeName.NA_LETTER);
		attrs.add(new Copies(1));
		attrs.add(new JobName("PrintPostScript", null));

		// DOES NOT WORK - you must pass SimpleDoc something that will turn into PostScript!
		Doc doc = new SimpleDoc(this, flavor, null);
		
		DocPrintJob printJob = printService.createPrintJob();
		printJob.print(doc, attrs);
	}
}
