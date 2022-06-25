package reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// tag::findAnnotatedClasses[]
/** Discover "plugins" or other add-in classes via Reflection using Annotations */
public class PluginsViaAnnotations {

	/**
	 * Find all classes in the given package which have the given
	 * class-level annotation class.
	 */
	public static List<Class<?>> findAnnotatedClasses(String packageName,
		Class<? extends Annotation> annotationClass) throws Exception {

		List<Class<?>> ret = new ArrayList<>();
		String[] clazzNames = ClassesInPackage.getPackageContent(packageName);
		for (String clazzName : clazzNames) {
			if (!clazzName.endsWith(".class")) {
				continue;
			}
			clazzName = clazzName.replace('/', '.').replace(".class", "");
			Class<?> c = null;
			try {
				c = Class.forName(clazzName);
			} catch (ClassNotFoundException ex) {
				System.err.println("Weird: class " + clazzName + 
					" reported in package but gave CNFE: " + ex);
				continue;
			}
			if (c.isAnnotationPresent(annotationClass) &&
					!ret.contains(c))
					ret.add(c);
			
		}
		return ret;
	}
	// end::findAnnotatedClasses[]

	// Note that CPD will consider these dupes, but they are near-identical
	// code blocks used in different presentations for quasi-didactic purposes.
	// IRL one would use a strategy object to eliminate the apparent duplication.
	
	// tag::findClassesWithAnnotatedMethods[]
	/**
	 * Find all classes in the given package which have the given
	 * method-level annotation class on at least one method.
	 */
	public static List<Class<?>> findClassesWithAnnotatedMethods(String packageName, 
			Class<? extends Annotation> methodAnnotationClass) throws Exception {
		List<Class<?>> ret = new ArrayList<>();
		String[] clazzNames = ClassesInPackage.getPackageContent(packageName);
		for (String clazzName : clazzNames) {
			if (!clazzName.endsWith(".class")) {
				continue;
			}
			clazzName = clazzName.replace('/', '.').replace(".class", "");
			Class<?> c = null;
			try {
				c = Class.forName(clazzName);
				// System.out.println("Loaded " + c);
			} catch (ClassNotFoundException ex) {
				System.err.println("Weird: class " + clazzName +
					" reported in package but gave CNFE: " + ex);
				continue;
			}
			for (Method m : c.getDeclaredMethods()) {
				// System.out.printf("Class %s Method: %s\n",
				//     c.getSimpleName(), m.getName());
				if (m.isAnnotationPresent(methodAnnotationClass) &&
						!ret.contains(c)) {
					ret.add(c);
				}
			}
		}
		return ret;
	}
	// end::findClassesWithAnnotatedMethods[]
}
