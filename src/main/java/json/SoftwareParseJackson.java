package json;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SoftwareParseJackson {

	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = new ObjectMapper(); // <1>

		String fileName = "/json/softwareinfo.json";
		InputStream jsonInput = SoftwareParseJackson.class.getResourceAsStream(fileName);
		if (jsonInput == null) {
			throw new NullPointerException("can't find" + fileName);
		}
		SoftwareInfo sware = mapper.readValue(jsonInput, SoftwareInfo.class);
		System.out.println(sware);
	}

}
