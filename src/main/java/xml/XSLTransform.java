import org.apache.xalan.xslt.*;
import java.net.*;
import java.io.*;

/**
 * Demonstrate transforming a file using XSLT, with old Apache Xalan API.
 * See also: JAXPTransform.
 */
public class XSLTransform {

	public static void main(String[] args) {

		try {
			// Require three input args
			if (args.length != 3) {
				System.out.println("Usage: java XSLTransform <input XML file> <input XSL file> <output file>");
				System.exit(1);
			}

			XSLTProcessor myProcessor = XSLTProcessorFactory.getProcessor();
			XSLTInputSource xmlSource = new XSLTInputSource(args[0]);
			XSLTInputSource xslStylesheet = new XSLTInputSource(args[1]);
			XSLTResultTarget xmlOutput = new XSLTResultTarget(args[2]);
			myProcessor.process(xmlSource, xslStylesheet, xmlOutput);
		}
		catch (org.xml.sax.SAXException exc) {
			System.err.println("Found invalid XML during processing:");
			exc.printStackTrace();
		}
	}
}
