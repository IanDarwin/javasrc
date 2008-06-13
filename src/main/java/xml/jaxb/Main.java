package xml.jaxb;

import java.io.FileWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.printf("Main.main(): %s%n", System.getProperty("user.dir"));
		// The user sets their preferences...
		Configuration c = new Configuration();
		c.setScreenName("idarwin");
		c.setColorName("inky green");
		c.setVerbose(true);
		
		// We set up JAXB
		JAXBContext jc = JAXBContext.newInstance("xml.jaxb");
		Marshaller saver = jc.createMarshaller();
		
		// We save their preferences
		Writer saveFile = new FileWriter("config.save");
		saver.marshal(c, saveFile);
		saveFile.close();
	}
}
