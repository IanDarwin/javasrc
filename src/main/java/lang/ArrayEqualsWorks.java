package lang;

import org.junit.Test;
import static org.junit.Assert.*;

/** This actually proves that array equals() methods do NOT work
 */
public class ArrayEqualsWorks {

	@Test
	public void testCharArrayEquals() {
		String type = "charset=12345";
		char[] array1 = type.toCharArray();
		char[] array2 = type.toCharArray();
		assertFalse("array equals() does not work", array1.equals(array2));
	}

	@Test
	public void testByteArrayEquals() {
		byte[] array1 = { 1, 2, 3 };
		byte[] array2 = { 1, 2, 3 };
		assertFalse("array equals() does not work", array1.equals(array2));
	}
	
	@Test
	public void testIntArrayEquals() {
		int[] array1 = { 1, 2, 3 };
		int[] array2 = { 1, 2, 3 };
		assertFalse("array equals() does not work", array1.equals(array2));
	}
	
	@Test
	public void testObjectArrayEquals() {
		Object[] array1 = { 1, 2, 3 };
		Object[] array2 = { 1, 2, 3 };
		assertFalse("array equals() does not work", array1.equals(array2));
	}

}
