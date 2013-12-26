package reflection;

import java.lang.reflect.Field;
import java.util.Calendar;

/** This class shows using Reflection to get a field from another class. */
// BEGIN main
public class FindField {

	public static void main(String[] unused) 
	throws NoSuchFieldException, IllegalAccessException {

		// Create instance of FindField
		FindField gf = new FindField();

		// Create instance of target class (YearHolder defined below).
		Object o = new YearHolder();

		// Use gf to extract a field from o.
		System.out.println("The value of 'currentYear' is: " +
			gf.intFieldValue(o, "currentYear"));
	}

	int intFieldValue(Object o, String name)
	throws NoSuchFieldException, IllegalAccessException {
		Class<?> c = o.getClass();
		Field fld = c.getField(name);
		int value = fld.getInt(o);
		return value;
	}
}

/** This is just a class that we want to get a field from */
class YearHolder {
	/** Just a field that is used to show getting a field's value. */
	public int currentYear = Calendar.getInstance().get(Calendar.YEAR);
}
// END main
