package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class RomanNumberFormatParameterizedTest {

	RomanNumberFormat nf;
	int julianYear;
	String romanYear;

	public void initRomanNumberFormatParameterizedTest(int julianYear, String romanYear) {
		this.julianYear = julianYear;
		this.romanYear = romanYear;
	}

	public static List<Object[]> getParams() {
		return Arrays.asList(data);
	}
	private static Object[][] data = {
			{ 1, "I" },
			{ 42, "XLII" },
			{ 678, "DCLXXVIII" },
			{ 1537, "MDXXXVII" },
			{ 1999, "MCMXCIX" },
			{ 2000, "MM" },
			{ 2001, "MMI" },
			{ 2013, "MMXIII" },
			{ 3999, "MMMCMXCIX" },
	};

	@BeforeEach
	void setUp() throws Exception {
		nf = new RomanNumberFormat();
	}

	@MethodSource("getParams")
	@ParameterizedTest
	public void format(int julianYear, String romanYear) {
		initRomanNumberFormatParameterizedTest(julianYear, romanYear);
			assertEquals(romanYear, nf.format(julianYear));
	}

	@MethodSource("getParams")
	@ParameterizedTest
	public void parse(int julianYear, String romanYear) {
		initRomanNumberFormatParameterizedTest(julianYear, romanYear);
		assertEquals(Long.valueOf(julianYear), nf.parseObject(romanYear, null));
	}
}
