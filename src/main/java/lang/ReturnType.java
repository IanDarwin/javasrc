/**
 * Simple Hello World demo program.
 */
public class ReturnType {

	int idemo() {
		return 4;
	}
	String demo() {
		return "Goodbye cruel world";
	}

	String d = demo();	// asigns "Goodbye cruel world" to d
	int e = demo();		// compile-time error
}
