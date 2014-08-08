package i18n;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.Test;

/** Because of static/classloader issues, this
 * MUST RUN IN ITS OWN JVM, not in a test suite!
 * @author Ian
 */
public class EnumIntlTest_es {
	
	@BeforeClass
	public static void setupLocale() {
		// This must happen BEFORE the first
		// reference to the EnumIntl class
		Locale.setDefault(new Locale("es"));
	}
	
	@Test
	public void testNorte() {
		assertEquals("Norte", EnumIntl.NORTH.getDescription());
	}
}
