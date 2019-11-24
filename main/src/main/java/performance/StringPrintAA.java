package performance;

public class StringPrintAA {
	public static void main(String[] argv) {
		Object o = "Hello World";
		for (int i=0; i<100000; i++) {
			System.out.println(
				new StringBuilder("<p><b>").append(o).append("</b></p>"));
		}
	}
}
