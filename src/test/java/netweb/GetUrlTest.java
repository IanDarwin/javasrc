package netweb;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class GetUrlTest {

	private static final URL URL = GetUrlTest.class.getResource("/netweb/GetUrlTestData.txt");

	@BeforeClass
	public static void setUpClass() throws Exception {
		System.out.println("URL is " + URL);
	}

	@Test
	public void test() throws Exception {
		String url = URL.toExternalForm();
		final List<Element> list = GetURLs.run(url);
		assertEquals(3, list.size());
	}

}
