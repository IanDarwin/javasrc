package lang;


/** This actually proves that array equals() methods do NOT work
 */
public class ArrayEqualsWorks {

	public static void main(String[] args) {
		ArrayEqualsWorks p = new ArrayEqualsWorks();
		p.testObjectArrayEquals();
		p.testIntArrayEquals();
		p.testByteArrayEquals();
		p.testCharArrayEquals();
		System.out.println("Q.E.D.");
	}

	/** Crude clone of JUnit stuff to void including test stuff into "lang" package */
	void assertFalse(String mesg, boolean expr) {
		if (expr) {
			throw new RuntimeException(mesg);
		}
	}

	// @Test
	public void testCharArrayEquals() {
		String type = "charset=12345";
		char[] array1 = type.toCharArray();
		char[] array2 = type.toCharArray();
		assertFalse("array equals() does not work", array1.equals(array2));
	}

	// @Test
	public void testByteArrayEquals() {
		byte[] array1 = { 1, 2, 3 };
		byte[] array2 = { 1, 2, 3 };
		assertFalse("array equals() does not work", array1.equals(array2));
	}
	
	// @Test
	public void testIntArrayEquals() {
		int[] array1 = { 1, 2, 3 };
		int[] array2 = { 1, 2, 3 };
		assertFalse("array equals() does not work", array1.equals(array2));
	}
	
	// @Test
	public void testObjectArrayEquals() {
		Object[] array1 = { 1, 2, 3 };
		Object[] array2 = { 1, 2, 3 };
		assertFalse("array equals() does not work", array1.equals(array2));
	}
}
