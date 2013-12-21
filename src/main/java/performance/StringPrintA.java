package performance;

// BEGIN main
public class StringPrintA {
	public static void main(String[] argv) {
		Object o = "Hello World";
		for (int i=0; i<100000; i++) {
			System.out.println("<p><b>" + o.toString() + "</b></p>");
		}
	}
}
// END main
