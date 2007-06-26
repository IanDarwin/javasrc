package patterns.creation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * A really really simple example of a configurable Factory
 */
public class Factory {

	public static void main(String[] args) throws Exception {
		Factory f = new Factory();
		Object p = f.getBean("renderer");
		System.out.println(p.getClass());
	}

	Properties props = new Properties();

	Factory() throws IOException{
		props.load(new FileInputStream("patterns/creation/factory.config"));
	}

	private Object getBean(String name) throws Exception {
		final String clazz = props.getProperty(name);
		final Class<? extends Object> c = Class.forName(clazz);
		final Object o = c.newInstance();
		return o;
	}

}
