import java.util.Scanner;
import junit.framework.TestCase;

/** A series of JUnit tests for java.util.Scanner.
 * Not trying to help Sun debug the thing; writing JUnit tests
 * is a good way to "test" one's understanding of a new API.
 */
public class ScannerTest extends TestCase {

	public void testDateString() {
		String sampleDate = "25 Dec 1988";	// K&R

		Scanner sDate = Scanner.create(sampleDate);
		int dom = sDate.nextInt();
		String mon = sDate.next().toString();
		int year = sDate.nextInt();

		assertEquals(dom, 25);
		assertEquals(mon, "Dec");
		assertEquals(year, 1988);
	}
}
