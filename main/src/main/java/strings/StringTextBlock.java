public class StringTextBlock {
	static
	String paraV1 = 
		"Creating long strings over multiple lines is tedious and error-prone.\n" +
		"The developer could forget the `+` at the end of a line, or more\n" +
		"commonly, forget the space or the `\\n' at the end of a line.";

	static
	String paraV2 = """
		Creating long strings over multiple lines is tedious and error-prone.
		The developer could forget the `+` at the end of a line, or more
		commonly, forget the space or the `\\n' at the end of a line.""";

	public static void main(String[] args) {
		System.out.println(paraV1);
		System.out.println(paraV2);
		System.out.println(paraV1.equals(paraV1));
	}
}
