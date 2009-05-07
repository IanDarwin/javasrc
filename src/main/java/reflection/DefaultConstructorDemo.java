package reflection;

import java.lang.reflect.Constructor;

/**
 * Demonstrate finding and using the no-argument constructor.
 */
public class DefaultConstructorDemo {
	
	public static class PublicClassWithDefaultConstr {
		// all-default
	}
	public static class PublicClassWithDefaultVisDefaultConstr {
		PublicClassWithDefaultVisDefaultConstr() { }
	}
	/* not public */ static class DefaultClassVizDefaultConstr {
		// all-default
	}

	// This one will fail.
	static class NoDefaultConstructor {
		public NoDefaultConstructor(Object o) {
			// just to make T not have a default constructor
		}
	}

	static Class<?>[] clazzes = { 
		PublicClassWithDefaultConstr.class,
		PublicClassWithDefaultVisDefaultConstr.class,
		NoDefaultConstructor.class,
		DefaultClassVizDefaultConstr.class
	};

	public static void main(String[] av) throws Exception {
		for (Class<?> c : clazzes) {
			System.out.println();
			System.out.println("Starting Class " + c);
			try {
				// Construct an object, as if by "new Type()"
				Constructor<?> con = c.getConstructor(new Class<?>[0]);
				System.out.println("Found default con: " + con);
				Object o = con.newInstance(new Object[0]);
				System.out.println("WORKED: " + o);

			} catch (Exception e) {
				System.out.println("FAIL: " + e);
			}
		}
	}
}
