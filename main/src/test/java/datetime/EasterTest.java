package datetime;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EasterTest {

	@ParameterizedTest
	@CsvSource(textBlock = """
		1900,'1900-04-15'
		1951,'1951-03-25'
		1977,'1977-04-10'
		2000,'2000-04-23'
		2001,'2001-04-15'
		2020,'2020-04-12'
		2023,'2023-04-09'git c
		""")
	public void testConvert(int year, String expected) {
		assertEquals(expected, Easter.findHolyDay(year).toString());
	}
}
