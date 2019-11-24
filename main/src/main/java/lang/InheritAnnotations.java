package lang;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@MyAnnotation
class C0 {
	// empty
}

/* No annotation here - is it inherited? */
class C1 extends C0 {
	// empty
}

/** If you run this you will find out
 * whether Annotations get inherited or not.
 */
public class InheritAnnotations {
	public static void main(String[] args) {
		for (Class<?> c : new Class<?>[]{ C0.class, C1.class} ) {
			System.out.printf("Class %s: Annotation is %s present%n",
					c.getName(),
					c.isAnnotationPresent(MyAnnotation.class) ? "" : "not");
		}
	}
}

/** Here's the annotation itself... */
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
	// nothing needed here
}
