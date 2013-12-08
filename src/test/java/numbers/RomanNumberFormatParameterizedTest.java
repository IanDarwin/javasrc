package numbers;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

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
	
	@Before
	public void setUp() throws Exception {
		nf = new RomanNumberFormat();
	}

	@Test
	public void doTest() {
			assertEquals(romanYear, nf.format(julianYear));
	}
}
