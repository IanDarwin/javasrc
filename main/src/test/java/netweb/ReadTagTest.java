package netweb;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Test the Tag Reader
 */
class ReadTagTest {
	
	String htmlText = "<html><head><foo><bar></bar></foo></head><body><p>Paragraph" + 
		"<a href='http://grelber/' name=\"grelber\">Grelber Info</a>";

	@Test
	void readAll() throws Exception {
		Reader is = new StringReader(htmlText);
		ReadTag red = new ReadTag(is);
		List<Element> readTags = red.readTags();
		assertNotNull(readTags, "list from readTags");
		assertEquals(7, readTags.size(), "HTML tags from readTags");
		for (Element e : readTags) {
			System.out.println(e);
		}
	}

	@Test
	void readSome() throws Exception {
		Reader is = new StringReader(htmlText);
		ReadTag red = new ReadTag(is);
		red.setWantedTags(new String[] { "a", "foo" });
		List<Element> readTags = red.readTags();
		assertNotNull(readTags, "list from readTags");
		assertEquals(2, readTags.size(), "any tags from readTags");
		assertEquals("http://grelber/", readTags.get(1).getAttribute("href"), "get href");
		for (Element e : readTags) {
			System.out.println(e);
		}
	}

	@Test
	void readNone() throws Exception {
		Reader is = new StringReader(htmlText);
		ReadTag red = new ReadTag(is);
		red.setWantedTags(new String[] { });
		List<Element> readTags = red.readTags();
		assertNotNull(readTags, "list from readTags");
		assertEquals(0, readTags.size(), "any tags from readTags");
		for (Element e : readTags) {
			System.out.println(e);
		}
	}

	@Test
	void readDocType() throws Exception {
		Reader is = new StringReader("<?xml version='1.0'?>");
		ReadTag red = new ReadTag(is);
		List<Element> list = red.readTags();
		assertNotNull(list, "list from readTags");
		assertEquals(1, list.size(), "any tags from readTags");
		Element el = (Element)list.getFirst();
		assertEquals("?xml", el.getType(), "list type");
	}

	@Test
	void readAttrs() throws Exception {
		Reader is = new StringReader(htmlText);
		ReadTag red = new ReadTag(is);
		red.setWantedTags(new String[] { "a" });
		List<Element> list = red.readTags();
		assertNotNull(list, "list from readTags");
		assertEquals(1, list.size(), "any tags from readTags");
		Element el = list.getFirst();
		assertEquals("a", el.getType(), "list type");
		System.out.println("HREF='" + el.getAttribute("href") + "'");
		assertEquals("grelber", el.getAttribute("name"), "name attribute");
		assertEquals("http://grelber/", el.getAttribute("href"), "href attribute");
		assertEquals("Grelber Info", el.getBodyText(), "body text");
	}
}
