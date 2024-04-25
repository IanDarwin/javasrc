package netweb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GetUrlTest {

	private static final URL URL = GetUrlTest.class.getResource("/netweb/GetUrlTestData.txt");

	@BeforeAll
	static void setUpClass() throws Exception {
		System.out.println("URL is " + URL);
	}

	@Test
	void test() throws Exception {
		String url = URL.toExternalForm();
		final List<Element> list = GetURLs.run(url);
		assertEquals(3, list.size());
	}

}
