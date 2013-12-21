package performance;

// BEGIN main
public class StringPrintB {
	public static void main(String[] argv) {
		Object o = "Hello World";
		for (int i=0; i<100000; i++) {
			System.out.print("<p><b>");
			System.out.print(o.toString());
			System.out.print("</b></p>");
			System.out.println();
		}
	}
}
// END main
