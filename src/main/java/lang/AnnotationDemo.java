package lang;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Resource;

// BEGIN main
/**
 * A sample annotation for types (classes, interfaces);
 * it will be available at run time.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationDemo {
	public boolean fancy() default false;
	public int order() default 42;
}

/** A simple example of using the annotation */
@AnnotationDemo(fancy=true)
@Resource(name="Dumbledore")
class FancyClassJustToShowAnnotation {

	/** Print out the annotations attached to this class */
	public static void main(String[] args) {
		Class<?> c = FancyClassJustToShowAnnotation.class;
		System.out.println("Class " + c.getName() + " has these annotations:");
		for (Annotation a : c.getAnnotations()) {
			if (a instanceof AnnotationDemo) {
				AnnotationDemo ad = (AnnotationDemo)a;
				System.out.println("\t" +a + 
					" with fancy=" + ad.fancy() + 
					" and order " + ad.order());
			} else {
				System.out.println("\tSomebody else's annotation: " + a);
			}
		}
	}
}
// END main
