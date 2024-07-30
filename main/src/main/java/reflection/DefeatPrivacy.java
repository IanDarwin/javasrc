package reflection;

import java.lang.reflect.Field;

// tag::main[]
class ClassWithPrivateField {
	@SuppressWarnings("unused") // Used surreptitiously below.
	private int p = 42;
	int q = 3;
}

/**
 * Demonstrate that it is, in fact, all too easy to access private members
 * of an object using Reflection.
 */
public class DefeatPrivacy {

	public static void main(String[] args) throws Exception {
		new DefeatPrivacy().process();
	}
	
	private void process() throws Exception {
		ClassWithPrivateField x = new ClassWithPrivateField();
		System.out.println(x);
		// System.out.println(x.p); // Won't compile
		System.out.println(x.q);
		Class<? extends ClassWithPrivateField> class1 = x.getClass();
		Field[] flds = class1.getDeclaredFields();
		for (Field f : flds) {
			f.setAccessible(true);	// bye-bye "private"
			System.out.println(f + "==" + f.get(x));
			f.setAccessible(false);	// reset to "correct" state
		}
	}
}
// end::main[]
