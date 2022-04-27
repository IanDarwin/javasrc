package xml.jaxb;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/** A demo program to show saving and reloading data with JAXB;
 */
public class ConfigDemo {

	public static void main(String[] args) throws Exception {
		System.out.println("JAXB Demo Starting");

		// Create a Config object, and test the empty config.
		Configuration c = new Configuration();
		
		// The user sets their preferences...
		c.setScreenName("idarwin");
		c.setColorName("inky green");
		c.setVerbose(true);

		Configuration c1 = new Configuration();
		c1.setScreenName(c.getScreenName());
		c1.setColorName(c.getColorName());
		c1.setVerbose(!c.isVerbose());	// negate field for test

		// BEGIN main
		// We set up JAXB: the context arg is the package name!
		JAXBContext jc = JAXBContext.newInstance("xml.jaxb");
		Marshaller saver = jc.createMarshaller();
		final File f = new File("config.save");
		
		// We save their preferences 
		// Configuration c = ... - set above
		Writer saveFile = new FileWriter(f);
		saver.marshal(c, saveFile);
		saveFile.close();

		// Confirm that the XML file got written
		System.out.println("JAXB output saved in " + f.getAbsolutePath());
		
		// Sometime later, we read it back in.
		Unmarshaller loader = jc.createUnmarshaller();
		Configuration c2 = (Configuration) loader.unmarshal(f);
		
		// Outside of the simulation, we test that what we
		// read back is the same as what we started with.
		// END main
	}
}
