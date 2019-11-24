package lang;

/** Variations on "And", "Or" operators
 *
 * @author Ian Darwin
 */
public class AndOp {

	public static void main(String[] args) {
		boolean taf = true & false;
		boolean fat = false & true;
		boolean taaf = true && false;
		@SuppressWarnings("unused")
		boolean faat = false && true;
		System.out.println(taf + "," + fat + "," + taaf + "," +faat);
	}
}
