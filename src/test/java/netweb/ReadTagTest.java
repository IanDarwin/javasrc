package netweb;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import junit.framework.TestCase;

/**
 * Test the Tag Reader
 */
public class ReadTagTest extends TestCase {
	
	String htmlText = "<html><head><foo><bar></head><body><p>Paragraph" + 
		"<a href='http://grelber/' name=\"grelber\">Grelber Info</a>";
	
	public void testReadAll() throws Exception {
		Reader is = new StringReader(htmlText);
		ReadTag red = new ReadTag(is);
		List<Element> readTags = red.readTags();
		assertNotNull("list from readTags", readTags);
		assertTrue("any tags from readTags", 7 == readTags.size());
		for (Element e : readTags) {
			System.out.println(e);
		}
	}
	
	public void testReadSome() throws Exception {
		Reader is = new StringReader(htmlText);
		ReadTag red = new ReadTag(is);
		red.setWantedTags(new String[] { "a", "foo" });
		List<Element> readTags = red.readTags();
		assertNotNull("list from readTags", readTags);
		assertTrue("any tags from readTags", 2 == readTags.size());
		for (Element e : readTags) {
			System.out.println(e);
		}
	}
	
	public void testReadNone() throws Exception {
		Reader is = new StringReader(htmlText);
		ReadTag red = new ReadTag(is);
		red.setWantedTags(new String[] { });
		List<Element> readTags = red.readTags();
		assertNotNull("list from readTags", readTags);
		assertTrue("any tags from readTags", 0 == readTags.size());
		for (Element e : readTags) {
			System.out.println(e);
		}
	}
	
	public void testReadDocType() throws Exception {
		Reader is = new StringReader("<?xml version='1.0'?>");
		ReadTag red = new ReadTag(is);
		List<Element> list = red.readTags();
		assertNotNull("list from readTags", list);
		assertTrue("any tags from readTags", 1 == list.size());
		Element el = (Element)list.get(0);
		assertEquals("list type", "?xml", el.getType());
	}
	
	public void testReadAttrs() throws Exception {
		Reader is = new StringReader(htmlText);
		ReadTag red = new ReadTag(is);
		red.setWantedTags(new String[] { "a" });
		List<Element> list = red.readTags();
		assertNotNull("list from readTags", list);
		assertTrue("any tags from readTags", 1 == list.size());
		Element el = (Element)list.get(0);
		assertEquals("list type", "a", el.getType());
		System.out.println("HREF='" + el.getAttribute("href") + "'");
		assertEquals("name attribute", "grelber", el.getAttribute("name"));
		assertEquals("href attribute", "http://grelber/", el.getAttribute("href"));
		assertEquals("body text", "Grelber Info", el.getBodyText());
	}
}
