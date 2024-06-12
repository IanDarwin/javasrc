package strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/** StringAlign Test program.  */
public class StringAlignParameterizedTest  {

	private static Object[][] mesgData = {{"JavaFun"}, {"JavaFun!"} };
	String mesg;

	/** Called repeatedly by Parameterized Test Runner */
	public void initStringAlignParameterizedTest(String mesg) {
		this.mesg = mesg;
	}
	
	public static List<Object[]> getParams() {
	        return Arrays.asList(mesgData);
	}

	@MethodSource("getParams")
	@ParameterizedTest
	public void left(String mesg) {
		initStringAlignParameterizedTest(mesg);
			assertEquals(mesg.substring(0, 5),
				new StringAlign(5, StringAlign.Justify.LEFT).format(mesg));
			String result = new StringAlign(10, StringAlign.Justify.LEFT).
					format(mesg);
			assertEquals(mesg, result.trim());
		assertEquals(10, result.length());
		
	}

	@MethodSource("getParams")
	@ParameterizedTest
	public void centre(String mesg) {
		initStringAlignParameterizedTest(mesg);
			assertEquals(mesg.substring(0, 5),
				new StringAlign(5, StringAlign.Justify.CENTER).format(mesg).trim());
			assertEquals(" " + mesg,
				new StringAlign(10, StringAlign.Justify.CENTER).format(mesg).substring(0, mesg.length()+1));
		
	}

	@MethodSource("getParams")
	@ParameterizedTest
	public void right(String mesg) {
		initStringAlignParameterizedTest(mesg);
			assertEquals(mesg.substring(0, 5),
				new StringAlign(5, StringAlign.Justify.RIGHT).format(mesg).trim());
			int len = mesg.length();
			String expected = "          ".substring(0, 10-len) + mesg;
			assertEquals(expected,
				new StringAlign(10, StringAlign.Justify.RIGHT).format(mesg));
		
	}
}
