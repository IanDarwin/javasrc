/**
 * StringBufferDemo: construct the same String
 * three different ways.
 */
public class StringBufferDemo {
	public static void main(String argv[]) {
		String s1 = "Hello" + ", " + "World";
		System.out.println(s1);

		StringBuffer sb2 = new StringBuffer();
		sb2.append("Hello");
		sb2.append(',');
		sb2.append(' ');
		sb2.append("World");
		String s2 = sb2.toString();
		System.out.println(s2);

		StringBuffer sb3 = new StringBuffer().append("Hello").
			append(',').append(' ').append("World");
		System.out.println(sb3.toString());
	}
}
