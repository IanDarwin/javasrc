package xml.jaxb;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

/** A test program to show saving and reloading data with JAXB;
 * cast as a JUnit test to ensure that the data reloaded correctly.
 */
public class Main {

	@Test
	public void main() throws Exception {

		// We create a Config object, and test the empty config.
		Configuration c = new Configuration();
		assertEquals(c,c);
		
		// The user sets their preferences...
		c.setScreenName("idarwin");
		c.setColorName("inky green");
		c.setVerbose(true);
		
		// We test a non-empty config
		assertEquals(c,c);

		
		// We set up JAXB
		JAXBContext jc = JAXBContext.newInstance("xml.jaxb");
		Marshaller saver = jc.createMarshaller();
		final File f = new File("config.save");
		
		// We save their preferences
		Writer saveFile = new FileWriter(f);
		saver.marshal(c, saveFile);
		saveFile.close();
		
		// Sometime later, we read it back in.
		Unmarshaller loader = jc.createUnmarshaller();
		Configuration c2 = (Configuration) loader.unmarshal(f);
		
		// Outside of the simulation, we test that what we
		// read back is the same as what we started with.
		assertEquals("saved and loaded back the object", c, c2);
	}
}
