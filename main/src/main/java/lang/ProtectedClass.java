package lang;

protected class ProtectedClass {		// EXPECT COMPILE ERROR
	public static void main(String[] a) {
		System.out.println("It seems you CAN have a protected class");
	}
}
