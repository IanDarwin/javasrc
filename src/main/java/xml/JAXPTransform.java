import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

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
