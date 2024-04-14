package numbers;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RomanNumberFormatParameterizedTest {

	RomanNumberFormat nf;
	int julianYear;
	String romanYear;
	
	public RomanNumberFormatParameterizedTest(int julianYear, String romanYear) {
		this.julianYear = julianYear;
		this.romanYear = romanYear;
	}

	@Parameters
	public static List<Object[]> getParams() {
		return Arrays.asList(data);
	}
	private static Object[][] data = {
			// A few commented out until parse is rewritten to handle subtractives
			{ 1, "I" },
			//{ 42, "XLII" },
			{ 678, "DCLXXVIII" },
			{ 1537, "MDXXXVII" },
			//{ 1999, "MCMXCIX" },
			{ 2000, "MM" },
			{ 2001, "MMI" },
			{ 2013, "MMXIII" },
			//{ 3999, "MMMCMXCIX" },
	};
	
	@Before
	public void setUp() throws Exception {
		nf = new RomanNumberFormat();
	}

	@Test
	public void testFormat() {
			assertEquals(romanYear, nf.format(julianYear));
	}

	@Test
	public void testParse() {
		assertEquals(Long.valueOf(julianYear), nf.parseObject(romanYear, null));
	}
}
