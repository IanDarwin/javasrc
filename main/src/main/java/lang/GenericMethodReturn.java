package lang;

/** Simple demo of a method whose return type is based on the type of an input parameter
  * @requires 5.0
  */
public class GenericMethodReturn {

	public static void main(String[] args) {
		String mess = fooble("Called with a String", "bleah");
		Integer num = fooble("Called with an int", 42);
		System.out.printf("Fooble correctly returned both a %s and a %s!\n", mess.getClass(), num.getClass());
	}

	/**
	 * The <T> declares a Type Parameter.
	 * The T by itself declares the return value.
	 * The second argument "T param" both declares the type value for "T" and the 
	 * value of parameter "param"
	 */
	public static <T> T fooble(String s, T param) {
		return param;
	}

}
