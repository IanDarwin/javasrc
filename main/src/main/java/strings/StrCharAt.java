package strings;

/** StrCharAt - show String.charAt()
 * @author Ian F. Darwin, https://darwinsys.com/
 */
// tag::main[]
public class StrCharAt {
    public static void main(String[] av) {
        String a = "A quick bronze fox";
		for (int i=0; i < a.length(); i++) { // no forEach, need the index
			String message = 
				"charAt is '%c', codePointAt is %3d, casted it's '%c'".formatted(
				a.charAt(i),
				a.codePointAt(i),
				(char)a.codePointAt(i));
			System.out.println(message);
		}
	}
}
// end::main[]
