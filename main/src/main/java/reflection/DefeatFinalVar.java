package reflection;

import java.lang.reflect.Field;

/**
 * You can access the final fields of a class, but not a record,
 * and even for the class, the results are not optimal.
 */
public class DefeatFinalVar {

	public static void main(String[] args) throws Exception {
		ClassWithFinalField x = new ClassWithFinalField();
		Class<?> c = x.getClass();
		Field f = c.getDeclaredField("secret");
		f.setAccessible(true);
		System.out.println("X.secret from field.get(): " + f.getInt(x));
		System.out.println("Trying to set X.secret to " + 123 + " via reflection");
		f.setInt(x, 123);
		System.out.println("X.secret from public getSecret() method: " + x.getSecret());
		System.out.println("X.secret from field.get(): " + f.getInt(x));
		System.out.println("X.secret from class-visible field access: " + x.secret);
		System.out.println();

		RecordsAllFinalFields y = new RecordsAllFinalFields(24);
		Class<?> c2 = x.getClass();
		Field f2 = c2.getDeclaredField("secret");
		f.setAccessible(true);
		System.out.println("Y.secret from field.get(): " + f2.getInt(x));
		System.out.println("Trying to set Y.secret to " + 123 + " via reflection");
		f.setInt(y, 123);
		System.out.println("Y.secret from public getSecret() method: " + y.getSecret());
		System.out.println("Y.secret from field.get(): " + f2.getInt(x));
		System.out.println("Y.secret from class-visible field access: " + y.secret());
	}

}

class ClassWithFinalField {
	final int secret = 24;
	public int getSecret() {
		return secret;
	}
}

record RecordsAllFinalFields(int secret) {
	public int getSecret() {
		return secret;
	}
}
