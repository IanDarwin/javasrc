package i18n;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

/** Because of static/classloader issues, this
 * MUST RUN IN ITS OWN JVM, not in a test suite!
 * @author Ian
 */
public class EnumIntlTest_es {
	
	@Test
	public void testNorte() {
		Locale.setDefault(new Locale("es"));
		assertEquals("Norte", EnumIntl.NORTH.getDescription());
	}
}
