package json;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.SoftwareInfo;

// tag::main[]
public class SoftwareParseJackson {
	final static String FILE_NAME = "/json/softwareinfo.json";

	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();                      // <1>

		InputStream jsonInput =
			SoftwareParseJackson.class.getResourceAsStream(FILE_NAME); // <2>
		if (jsonInput == null) {
			throw new NullPointerException("can't find " + FILE_NAME);
		}
		SoftwareInfo sware = mapper.readValue(jsonInput, SoftwareInfo.class); // <3>
		System.out.println(sware);
	}

}
// end::main[]
