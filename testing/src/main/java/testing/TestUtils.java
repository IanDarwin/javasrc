package testing;

import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Try to automate some testing, with mixed results.
 * XXX TOTAL FAIL THIS IS - if field name is not
 * the same as the Properties name
 * @author Ian
 */
public class TestUtils {

	 /**
	 * Describe a Property of a class; if its getter/setter are
	 * non-null access it that way, else access it via "raw".
	 */
	static class Prop {
		String name;
		Field rawField;
		Method getter, setter;
		Prop(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return "Prop(" + name + ")";
		}
	 }

	/*
	 * Overload of equals for use from 'normal' equals methods
	 * to perform an exhaustive comparison (using Reflection)
	 * on all fields within the class.
	 * If both objects are null (not possible in the usual
	 * use case), treat as true. If one is null, safely
	 * return false. Otherwise, use the Reflection API.
	 * @param o1 An object to compare
	 * @param o2 Another object to compare
	 * @return True iff o1 and o2 should be equal under the general contract of Object.equals
	 * @see java.lang.Object#equals(Object o2);
	 */
	public static boolean equals(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		// Slight variation on equals() contract: if both
		// are null, treat as same instead of NPE
		if (o1 == null && o2 == null) {
			return true;
		}
		// One is not null & the other is, safely return false
		if (o1 == null || o2 == null) {
			return false;
		}
		Class<?> c1 = o1.getClass();
		Class<?> c2 = o2.getClass();

		// Class objects are singleton-like, compare with ==
		if (c1 != c2) {
			throw new IllegalArgumentException("Cannot compare " + c1 + " with " + c2);
		}

		Map<String, Prop> props = getProperties(c1);
		List<String> propNames = new ArrayList<>(props.keySet());
		for (String name : propNames) {
			System.err.println("Trying property " + name);
			final Prop prop = props.get(name);
			if (prop == null) {
				throw new IllegalStateException("No Prop descriptor found for " + name);
			}
			if (prop.rawField == null) {
				throw new IllegalStateException("No RawField in Prop descriptor for " + name);
			}
			if (!propsEquals(prop.rawField, o1, o2)) {
				return false;
			}
		}
		return true;
	}


	/**
	 * A debugging method to report on fields that differ, *NOT* using the
	 * target objects' equals() method.
	 * @param o1 The first input
	 * @param o2 The second input
	 * @return a List of the differences
	 * @throws java.lang.Exception On any error
	 */
	public static List<String> compareAll(Object o1, Object o2) throws Exception  {
		final List<String> diffs = new ArrayList<String>();
		if (o1.getClass() != o2.getClass()) {
			fail("Can't compare different classes");
		}
		final Field[] fields = o1.getClass().getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true); // yes, we compare private fields
			Object f1 = f.get(o1);
			Object f2 = f.get(o2);
			if ((f1 == null) != (f2 == null)) { // only one is null => different
				diffs.add(String.format("%s(%s,%s)", f.getName(), f1, f2)); 
				continue;
			}
			if (f1 == null) // both are null => same
				continue;
			if (!f1.equals(f2)) {
				diffs.add(String.format("%s(%s,%s)", f.getName(), f1, f2));
			}
		}
		return diffs;
	}

	/**
	 * A JUnit-like assertion method that uses reflection to
	 * ensure that no properties have default values; useful for
	 * testing (possibly large) Constructors to ensure that all
	 * properties are being set (and set correctly, e.g., gotta
	 * love those copy-and-paste errors in legacy constructors
	 * that are set by hand...).
	 * @param o The object (of any type) to be tested.
	 * @throws java.lang.Exception On any error
	 */
	public static void assertNoDefaultProperties(Object o) throws Exception {
		if (o == null) {
			throw new NullPointerException("Object may not be null");
		}
		Class<?> c = o.getClass();
		Map<String, Prop> props = getProperties(c);
		List<String> propNames =
			new ArrayList<String>(props.keySet());
		for (String name : propNames) {
			Prop p= props.get(name);
			Object target = null;
			if (p.rawField != null) {
				target = p.rawField.get(o);
			} else if (p.getter != null) {
				target = p.getter.invoke(o, new Object[0]);
			}
			System.out.printf("Field %s, value %s%n", name, target);
			if (target == null || target.equals(Boolean.FALSE)) {
				throw new AssertionError("property " + p.name + " is default");
			}
		}
		System.out.println("assertNoDefaultProperties didn't find any problems");
	}

	/** Extract a map of all properties */
	private static Map<String, Prop> getProperties(Class<?> c1) {
		Map<String, Prop> propsMap = new HashMap<String, Prop>();
		Field[] fields = c1.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);  // bye-bye "private"
			String fieldName = f.getName();
			
			// Put the entry in the props map,
			// unless we already put it b/c of a method
			Prop p = propsMap.get(fieldName);
			if (p == null) {
				p = new Prop(fieldName);
				propsMap.put(fieldName, p);
			}
			p.rawField = f;
		}
		
		Method[] methods = c1.getDeclaredMethods();
		for (Method m : methods) {
			m.setAccessible(true);  // bye-bye "private"
			String methodName = m.getName();
			if (!(methodName.startsWith("get") || methodName.startsWith("set"))) {
				continue;
			}
			String propName = methodName.substring(3);
			propName = propName.substring(0,1).toLowerCase() +
				propName.substring(1);
			// Put the entry in the props map,
			// unless we already put it b/c of f's mate.
			Prop p = propsMap.get(propName);
			if (p == null) {
				p = new Prop(propName);
				propsMap.put(propName, p);
			}
			if (methodName.startsWith("get")) {
				p.getter = m;
			} else if (methodName.startsWith("set")) {
				p.setter = m;
			} else {
				throw new IllegalStateException("invalid name " + methodName);
			}
		}
		return propsMap;
	}

	private static boolean propsEquals(Member member, Object o1, Object o2) {
		if (member == null) {
			throw new IllegalStateException("Member is null in propsEquals");
			//return false;
		}
		try {
			if (member instanceof Field) {
				Field f = (Field)member;
				System.out.println(String.format("EqualsUtils.propsEquals(%s)", f.getName()));
				Object val1 = f.get(o1);
				Object val2 = f.get(o2);
				return val1.equals(val2);
			} else if (member instanceof Method) {
				Method m = (Method)member;
				String methodName = m.getName();
				if (methodName.startsWith("set")) {
					// only test getters here - see TGS
					return true;
				}
				Object v1 = m.invoke(o1, new Object[0]);
				Object v2 = m.invoke(o2, new Object[0]);
				if (v1 == null && v2 == null) {
					return true;
				}
				if (v1 == null || v2 == null) {
					return false;
				}
				return v1.equals(v2);
			} else
				throw new IllegalArgumentException(
					"Internal error: member " + member + " neither Method nor Field");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		/*NOTREACHED*/
	}
}
