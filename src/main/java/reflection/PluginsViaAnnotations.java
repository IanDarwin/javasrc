package reflection;

public class PluginsViaAnnotations {

	public static void findAnnotatedClasses(String packageName, Class<?> classAnnotation) {
		// Get the list of classes in the given package(s) (see RECIPEXX);
		// Check if the class is annotated;
		// If so, save the name and Class descriptor for later use.
	}
	
	public static void findAnnotatedClassesWithMethods(String pkg, Class<?> classAnnotation, Class<?> methodAnnotation) {
		//  Get the list of classes in the given package(s) (see RECIPEXX);
		// If you are using a class-level annotation, check if the class is annotated;
		// If this class is still of interest, get a list of its methods;
		// For each method, see if it contains a given method-specific annotation;
		// If so, add the class and method to a list of invocable methods.
	}
	
	public static void main(String[] args) {
		throw new RuntimeException("Not written yet");
	}
}
