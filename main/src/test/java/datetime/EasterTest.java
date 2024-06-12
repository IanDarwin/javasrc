package datetime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EasterTest {

	@ParameterizedTest(name = "Year {0} should declare Easter as {1}")
	@CsvSource(textBlock = """
		1900,'1900-04-15'
		1951,'1951-03-25'
		1977,'1977-04-10'
		2000,'2000-04-23'
		2001,'2001-04-15'
		2020,'2020-04-12'
		2023,'2023-04-09'
		""")
	void convert(int year, String expected) {
		assertEquals(expected, Easter.findHolyDay(year).toString());
	}
}
