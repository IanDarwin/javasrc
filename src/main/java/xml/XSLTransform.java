package xml;

import org.apache.xalan.xslt.XSLTInputSource;
import org.apache.xalan.xslt.XSLTProcessor;
import org.apache.xalan.xslt.XSLTProcessorFactory;
import org.apache.xalan.xslt.XSLTResultTarget;

/**
 * Demonstrate transforming a file using XSLT, with Apache Xalan API.
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

			myProcessor.process(new XSLTInputSource(args[0]),
					new XSLTInputSource(args[1]),
					new XSLTResultTarget(args[2]));
		}
		catch (org.xml.sax.SAXException exc) {
			System.err.println("Found invalid XML during processing:");
			exc.printStackTrace();
		}
	}
}
