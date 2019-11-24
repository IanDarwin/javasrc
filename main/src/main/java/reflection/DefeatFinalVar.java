package reflection;

import java.lang.reflect.*;

/**
 * You can modify the final fields of a class, but the results are not optimal.
 */
public class DefeatFinalVar {

	public static void main(String[] args) throws Exception {
		X x = new X();
		Class<?> c = x.getClass();
		Field f = c.getDeclaredField("secret");
		f.setAccessible(true); // gets past the "private" keyword
		System.out.println("X.secret from field.get(): " + f.getInt(x));
		System.out.println("Trying to set X.secret to " + 123 + " via reflection");
		f.setInt(x, 123);
		System.out.println("X.secret from public getSecret() method: " + x.getSecret());
		System.out.println("X.secret from field.get(): " + f.getInt(x));
		System.out.println("X.secret from class-visible field access: " + x.secret);
	}
	
	private static class X {
		private final int secret = 24;
		public int getSecret() {
			return secret;
		}
	}

}
