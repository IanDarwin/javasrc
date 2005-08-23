package performance;

import java.util.regex.Pattern;

/**
 * Test string replacement done by regex.
 */
public class PerfTimeRegex implements Timeable {

	public static final String QUOTEY_STRING = "'a'a'a'a'a'a'a'a'";
	public static final String EXPECTED_RESULT = "''a''a''a''a''a''a''a''a''";

	static final Pattern patt =  Pattern.compile("'");
	
	public String convert(String inputString) {	
		if (inputString == null) {
			throw new NullPointerException("input string is null");
		}
		return patt.matcher(inputString).replaceAll("''");
	}

	public void init(String[] args) {
		String result = convert(QUOTEY_STRING);
		if (!EXPECTED_RESULT.equals(result)) {
			throw new IllegalStateException(
			"Wanted " + EXPECTED_RESULT + " but got " + result);
		}
		System.out.println(this.getClass().getName() + " gets right answer.");
	}

	public void methodUnderTest() {
		convert(QUOTEY_STRING);
	}
}
