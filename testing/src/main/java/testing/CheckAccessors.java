package testing;

import static org.junit.Assert.assertEquals;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.darwinsys.testing.RandomDataGenerator;

/** A JUnit helper to test the setter/getter pairs in
 * the given class(es).
 */
public class CheckAccessors {

	private final static boolean debug = true;
	
	public static void debug(String s) {
		if (debug) {
			System.out.println(s);
		}
	}
	
	public static void process(final String className) throws Exception {
		final Class<?> c = Class.forName(className);
		process(c);
	}
	
	private static boolean isPublic(Member m) {
		return Modifier.isPublic(m.getModifiers());
	}
	
	public static void process(final Class<?> c)  throws Exception {
		// Many class-like things cannot be instantiated:
		if (c.isInterface() ||
			c.isEnum() ||
			c.isAnnotation()) {
			debug(c + " not an instantiable class");
			return;
		}
		// Nor can abstract classes.
		if (Modifier.isAbstract(c.getModifiers())) {
			debug(c + " is abstract");
			return;
		}
		// c.getConstructor(new Class[0]) fails
		final Object instance;
		try {
			instance = c.getConstructor().newInstance();
		} catch (Exception e) {
			debug(c + ": newInstance fail: " + e);
			return;
		}
		
		// Now get the list of "properties" (i.e.,
		// setter/getter pairs.
		// Don't get fields from any superclass, just
		// the class under test:
		final Class<?> stopClass = c.getSuperclass();
		final BeanInfo beanInfo = Introspector.getBeanInfo(c, stopClass);
		final PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor p : props) {
			final String propName = p.getName();
			Method writeMethod = p.getWriteMethod();
			if (writeMethod == null || 
				!isPublic(writeMethod)) {
				// no set method not worth logging, i.e., Object.getClass()
				continue;
			}
			final Class<?> type = p.getPropertyType();
			Object value = RandomDataGenerator.getRandomValue(type);
			if (value == null) {
				continue;	// can't test this setter/getter
			}
			writeMethod.invoke(instance, new Object[]{value});
			
			final Method readMethod = p.getReadMethod();
			if (readMethod == null)
				continue;
			if (!isPublic(readMethod)) {
					// non-public get method not worth logging
					continue;
				}
			Object back = readMethod.invoke(instance, new Object[0]);
			assertEquals(c.getName() + "." + propName, value, back);
		}
	}
}
