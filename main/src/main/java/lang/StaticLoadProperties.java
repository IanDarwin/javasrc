package lang;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class StaticLoadProperties {

	static final String PROPS_FILE = "props.txt";

	static Properties props = new Properties();

	static {
		try {
			props.load(new FileInputStream(PROPS_FILE));
		} catch (IOException ex) {
			throw new ExceptionInInitializerError("Failed to load properties from " + PROPS_FILE);
		}
	}

	Object createObject() {
		try {
			return Class.forName(props.getProperty("main-class")).getConstructor().newInstance();
		} catch (Exception ex) {
			throw new ExceptionInInitializerError("Can't instantiate:" + ex);
		}
	}

	public static void main(String[] args) {
		System.out.println("About to create");
		System.out.println(new StaticLoadProperties().createObject());
		System.out.println("Done create");
	}
}
