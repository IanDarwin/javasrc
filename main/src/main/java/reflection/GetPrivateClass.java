package reflection;

public class GetPrivateClass {

	/**
	 * Show that code can uncover the fields of a private class.
	 */
	public static void main(String[] args) {
		X x = new X();
		GetPrivateClassPart2.discover(x);
	}
	
	private static class X {
		private int secret = 24;
	}

}
