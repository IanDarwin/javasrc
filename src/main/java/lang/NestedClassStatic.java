package lang;

public class NestedClassStatic {
	/** Just show that Nested Classes may be static */
	static class V {
	}
	public static void main(String[] args) {
		V v = new V();
		System.out.println(v);
		v = new V();
		System.out.println(v);
	}
}
