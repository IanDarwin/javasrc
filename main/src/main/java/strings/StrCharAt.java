package strings;

/** StrCharAt - show String.charAt()
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// tag::main[]
public class StrCharAt {
    public static void main(String[] av) {
        String a = "A quick bronze fox";
		for (int i=0; i < a.length(); i++) { // Don't use foreach
			String message = String.format(
				"charAt is '%c', codePointAt is %3d, casted it's '%c'",
					 a.charAt(i),
					 a.codePointAt(i),
					 (char)a.codePointAt(i));
			System.out.println(message);
		}
	}
}
// end::main[]
