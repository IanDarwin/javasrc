package i18n;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests an internationalized enum with
 * a non-default language.
 * @author Ian
 */
class EnumIntlTest_es {
	
	static Locale savedLocale;

	@BeforeAll
	static void setupLocale() {
		// This must happen BEFORE the first
		// reference to the EnumIntl class
		savedLocale = Locale.getDefault();
		Locale.setDefault(Locale.forLanguageTag("es"));
	}

	@AfterAll
	static void resetLocale() {
		Locale.setDefault(savedLocale);
	}

	@Test
	void norte() {
		assertEquals("Norte", EnumIntl.NORTH.getDescription());
	}
}
