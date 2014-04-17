package xml;

import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/** Illustrate simplest use of JAXP to transform using XSL.
 */
// BEGIN main
public class JAXPTransform {
	
	/**
	 * @param args three filenames: XML, XSL, and Output (this order is historical).
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		// Require three input args
		if (args.length != 3) {
			System.out.println(
			"Usage: java JAXPTransform inputFile.xml inputFile.xsl outputFile");
			System.exit(1);
		}
		
		// Create a transformer object
		Transformer tx = TransformerFactory.newInstance().newTransformer(
				new StreamSource(new File(args[1]))); // not 0
		
		// Use its transform() method to perform the transformation
		tx.transform(new StreamSource(new File(args[0])), // not 1
				new StreamResult(new File(args[2])));
	}
}
// END main
