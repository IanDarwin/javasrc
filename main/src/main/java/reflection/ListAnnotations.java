package reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ListAnnotations {

	public static void main(String[] av) throws Exception {
	
		if (av.length == 0) {
			System.out.println("Usage: java ListAnnotations class [...]");
			System.exit(1);
		}
		for (String clazzName : av) {
			Class<?> c = Class.forName(clazzName);
			System.out.println(c);
			for (Method m : c.getMethods()) {
				int n = m.getAnnotations().length;
				System.out.println("Method " + m.getName() + " has " + n + " annotations.");
				if (n > 0) {
					for (Annotation a : m.getAnnotations()) {
						System.out.println("  " + a);
					}
				}
			}
		}
	}
}
