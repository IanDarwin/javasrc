import java.util.*;

/** Convert an Array to a Vector. */
public class ArrayToVector {
	public static void main(String[] args) {
		Object[] a1d = {
			"Hello World",
			new Date(),
			Calendar.getInstance(),
		};
		// Arrays.asList(Object[]) --> List
		List l = Arrays.asList(a1d);

		// Vector contstructor takes Collection
		// List is a subclass of Collection
		Vector v;
		v = new Vector(l);

		// Or, more simply:
		v = new Vector(Arrays.asList(a1d));

		// Just to prove that it worked.
		Enumeration e = v.elements();
		while (e.hasMoreElements()) {
			System.out.println(e.nextElement());
		}
	}
}
