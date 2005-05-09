package cookiecutter;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import junit.framework.TestCase;

public class CookieAccessorTest extends TestCase{
	public void testRead() throws Exception {
		String oneTestData = "# Netscape HTTP Cookie File\n"
				+ "# http://www.netscape.com/newsref/std/cookie_spec.html\n"
				+ "# This is a generated file!  Do not edit.\n"
				+ "\n"
				+ "www.computerworld.com	FALSE	/home/print.nsf/all	FALSE	1076313600	agent_it	9750405488095\n"
				+ "secure.webconnect.net	FALSE	/cgi-bin	FALSE	1234099454	6772	000112172107480710011001\n"
				+ "www.teknosurf3.com		FALSE	/cgi-bin	FALSE	1109631679	teknoacc	3063250613\n";
		Reader rdr = new StringReader(oneTestData);
		List<Cookie> list = new CookieAccessor().read(rdr, "test cookies");
		assertEquals("read all cookies", 3, list.size());
		Cookie c = list.get(2);
		assertNotNull("Get last cookie from list", c);
		assertEquals("get domain", "www.teknosurf3.com", c.getDomain());
		assertEquals("get path", "/cgi-bin", c.getPath());
		assertEquals("get name", "teknoacc", c.getName());
		assertEquals("get value", "3063250613", c.getValue());
	}
}
