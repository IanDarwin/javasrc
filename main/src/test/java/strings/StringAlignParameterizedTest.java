package strings;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/** StringAlign Test program.  */
@RunWith(Parameterized.class)
public class StringAlignParameterizedTest  {

	private static Object[][] mesgData = {{"JavaFun"}, {"JavaFun!"} };
	String mesg;
	
	/** Called repeatedly by Parameterized Test Runner */
	public StringAlignParameterizedTest(String mesg) {
		this.mesg = mesg;
	}
	
	@Parameters
	 public static List<Object[]> getParams() {
	        return Arrays.asList(mesgData);
	}

	@Test
	public void testLeft() {
			assertEquals(mesg.substring(0, 5),
				new StringAlign(5, StringAlign.Justify.LEFT).format(mesg));
			String result = new StringAlign(10, StringAlign.Justify.LEFT).
					format(mesg);
			assertEquals(mesg, result.trim());
			assertEquals(result.length(), 10);
		
	}

	@Test
	public void testCentre() {
			assertEquals(mesg.substring(0, 5),
				new StringAlign(5, StringAlign.Justify.CENTER).format(mesg).trim());
			assertEquals(" " + mesg,
				new StringAlign(10, StringAlign.Justify.CENTER).format(mesg).substring(0, mesg.length()+1));
		
	}

	@Test
	public void testRight() {
			assertEquals(mesg.substring(0, 5),
				new StringAlign(5, StringAlign.Justify.RIGHT).format(mesg).trim());
			int len = mesg.length();
			String expected = "          ".substring(0, 10-len) + mesg;
			assertEquals(expected,
				new StringAlign(10, StringAlign.Justify.RIGHT).format(mesg));
		
	}
}
