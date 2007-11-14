package websvcregress;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import websvcutil.DateUtil;

import junit.framework.TestCase;

public class DateUtilTest extends TestCase {

	/*
	 * Test method for 'util.DateUtil.getDateFormatInstance()'
	 */
	public void testGetDateFormatInstance() {
		assertTrue("getsInstance", DateUtil.getDateFormatInstance() instanceof SimpleDateFormat);
	}

	public void testFormatAndParse() throws Exception {
		String date = "2112-04-24T12:30";
		DateFormat df = DateUtil.getDateFormatInstance();
		assertEquals("testformatandparse", date, df.format(df.parse(date)));
	}
}
