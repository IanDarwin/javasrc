package netweb;

public class GetResource {
	public static void main(String[] argv) {
		Class<?> c = GetResource.class;
		java.net.URL u = c.getResource("GetResource.java");
		System.out.println(u);
	}
}
