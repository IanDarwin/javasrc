import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/** Illustrate simplest use of JAXP to transform using XSL */
public class JAXPTransform {
	public static void main(String[] args) throws Exception {

		// Create a transformer object
		Transformer tx = TransformerFactory.newInstance().newTransformer(
			new StreamSource(new File("parents.xsl")));

		// Use its transform() method to perform the transformation
		tx.transform(
			new StreamSource(new File("parents+dtd.xml")),
			new StreamResult(System.out));
	}
}
