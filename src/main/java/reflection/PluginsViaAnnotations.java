package reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// BEGIN findAnnotatedClasses
/** Discover "plugins" or other add-in classes via Reflection using Annotations */
public class PluginsViaAnnotations {

	/**
	 * Find all classes in the given package which have the given
	 * class-level annotation class.
	 */
	public static List<Class<?>> findAnnotatedClasses(String packageName,
		Class<? extends Annotation> annotationClass) throws Exception {

		List<Class<?>> ret = new ArrayList<>();
		String[] classes = ClassesInPackage.getPackageContent(packageName);
		for (String clazz : classes) {
			Class<?> c = Class.forName(clazz);
			if (c.isAnnotationPresent(annotationClass))
				ret.add(c);
		}
		return ret;
	}
	// END findAnnotatedClasses
	
	// BEGIN findClassesWithAnnotatedMethods
	/**
	 * Find all classes in the given package which have the given
	 * method-level annotation class on at least one method.
	 */
	public static List<Class<?>> findClassesWithAnnotatedMethods(String packageName, 
			Class<? extends Annotation> methodAnnotationClass) throws Exception {
		List<Class<?>> ret = new ArrayList<>();
		String[] classes = ClassesInPackage.getPackageContent(packageName);
		for (String clazz : classes) {
			Class<?> c = Class.forName(clazz);
			for (Method m : c.getMethods()) {
				if (m.isAnnotationPresent(methodAnnotationClass)) {
					ret.add(c);
				}
			}
		}
		return ret;
	}
	// END findClassesWithAnnotatedMethods
}
