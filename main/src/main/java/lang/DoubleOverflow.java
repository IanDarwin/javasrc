package lang;

/** Show that doubles overflow without warning
 */
public class DoubleOverflow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double d = Double.MAX_VALUE;
		System.out.println(d);
		d = d + 1;
		System.out.println(d);
		++d; // Is this valid? :-)
		System.out.println(d);
		d *= Integer.MAX_VALUE;
		System.out.println(d);
	}

}
