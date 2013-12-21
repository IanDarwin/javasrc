package strings;

// BEGIN main
public class SubStringDemo {
	public static void main(String[] av) {
		String a = "Java is great.";
		System.out.println(a);
		String b = a.substring(5);	// b is the String "is great."
		System.out.println(b);
		String c = a.substring(5,7);// c is the String "is"
		System.out.println(c);
		String d = a.substring(5,a.length());// d is "is great."
		System.out.println(d);
	}
}
// END main
