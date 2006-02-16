package regress;

import java.net.MalformedURLException;

import util.URLUtil;

import junit.framework.TestCase;

public class URLUtilTest extends TestCase {
	
	public void testOne() throws MalformedURLException {
		String testdata = "http://foo/bar";
		System.out.println(testdata);
		String url = URLUtil.changeURLPort(testdata, 7777);
		System.out.println(url);
		assertEquals("sevenses", "http://foo:7777/bar", url);
	}
}
