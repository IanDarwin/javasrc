package lang;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A sample annotation for types (classes, interfaces);
 * it will be available at run time.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationDemo {
	public static final int N = 42;
	public boolean useless() default false;
}

/** A simple example of using the annotation */
@AnnotationDemo(useless=true)
class UselessClassJustToShowAnnotation {

	/** Print out the annotations attached to this class */
	public static void main(String[] args) {
		Class c = UselessClassJustToShowAnnotation.class;
		System.out.println(c + " has these annotations:");
		for (Annotation a : c.getAnnotations()) {
			System.out.println(a);
		}
	}
}