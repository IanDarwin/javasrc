package reflection;

import java.lang.reflect.Method;

/**
 * Reflect1 shows the information about the class named in argv[0].
 * "Reflectance" is used to look up that information.
 */
public class Reflect1 {
	public static void main(String[] args)
	{
		new Reflect1().run(args);
	}
	public void run(String classes[]) {
		for (int i=0; i<classes.length; i++)
			try {
				Class<?> c = Class.forName(classes[i]);
				Method methods[] = c.getMethods();
				for (int m = 0; m < methods.length; m++)
					System.out.println(methods[m].toString());
			} catch (ClassNotFoundException e) {
				System.err.println("Error: Class " + 
					classes[i] + " not found!");
			} catch (Exception e) {
				System.err.println(e);
			}
	}
}

