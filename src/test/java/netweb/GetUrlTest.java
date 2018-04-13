package netweb;

import java.net.URL;

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
		String[] argv = { url };
		GetURLs.main(argv);
	}

}
