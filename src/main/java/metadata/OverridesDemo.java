package metadata;

/** Simple demonstation of Metadata being used to verify
 * that a method does in fact override (not overload) a method
 * from the parent class. This class provides the method.
 */
abstract class Top {
	public abstract void myMethod(Object o);
}

/** Simple demonstation of Metadata being used to verify
 * that a method does in fact override (not overload) a method
 * from the parent class. This class is supposed to do the overriding,
 * but deliberately introduces an error to show how the 1.5 compiler
 * behaves (-source 1.5 required).
 */
class Bottom {
	@Overrides public void myMethod(String s) {	// EXPECT COMPILE ERROR
		// Do something here...
	}
}
