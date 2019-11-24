package i18n;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests an internationalized enum with
 * a non-default language.
 * @author Ian
 */
public class EnumIntlTest_es {
	
	static Locale savedLocale;
	
	@BeforeClass
	public static void setupLocale() {
		// This must happen BEFORE the first
		// reference to the EnumIntl class
		savedLocale = Locale.getDefault();
		Locale.setDefault(Locale.forLanguageTag("es"));
	}
	
	@AfterClass
	public static void resetLocale() {
		Locale.setDefault(savedLocale);
	}
	
	@Test
	public void testNorte() {
		assertEquals("Norte", EnumIntl.NORTH.getDescription());
	}
}
