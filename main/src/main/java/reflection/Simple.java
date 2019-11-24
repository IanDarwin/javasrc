package reflection;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Show a couple of things you can do with a Class object.
 * @author Ian F. Darwin, ian darwinsys.com
 */
public class Simple {
	public static void main(String[] argv) throws Exception {
		Class<Date> c = (Class<Date>) Class.forName("java.util.Date");

		Date d = c.newInstance();
		System.out.println("Today is " + d);

		System.out.println("d is of class " + c.getName());

		Method[] methods = c.getMethods();
		// Just print the first few to avoid filling the screen.
		for (int i=0; i<10; i++)
			System.out.println(methods[i].toString());
	}
}
