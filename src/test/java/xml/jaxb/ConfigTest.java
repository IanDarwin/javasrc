package xml.jaxb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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
public class ConfigTest {

	@Test
	public void test() throws Exception {
		System.out.println("JAXB Testing");

		// Create a Config object, and test the empty config.
		Configuration c = new Configuration();
		assertEquals(c,c);
		
		// The user sets their preferences...
		c.setScreenName("idarwin");
		c.setColorName("inky green");
		c.setVerbose(true);

		// We test a non-empty config
		assertEquals(c,c);
		
		Configuration c1 = new Configuration();
		c1.setScreenName(c.getScreenName());
		c1.setColorName(c.getColorName());
		c1.setVerbose(!c.isVerbose());	// negate field for test

		// Test non-equality
		assertNotEquals(c, c1);

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
		assertTrue(f.exists());
		System.out.println("JAXB output saved in " + f.getAbsolutePath());
		
		// Sometime later, we read it back in.
		Unmarshaller loader = jc.createUnmarshaller();
		Configuration c2 = (Configuration) loader.unmarshal(f);
		
		// Outside of the simulation, we test that what we
		// read back is the same as what we started with.
		assertEquals("saved and loaded back the object", c, c2);
		// END main

		// Bit o' cleanup
		f.delete();
	}
}
