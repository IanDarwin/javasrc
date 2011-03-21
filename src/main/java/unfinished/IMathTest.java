package unfinished;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the IMath class
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class IMathTest {
	@Test
	public void main() {
		assertEquals("0", 0, isqrt(0));
		assertEquals("1", 1, isqrt(1));
		assertEquals("1", 1, isqrt(2));
		assertEquals("2", 2, isqrt(4));
	}
}
