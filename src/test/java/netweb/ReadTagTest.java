package netweb;

import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

/**
 * Test the Tag Reader
 * 
 */
public class ReadTagTest extends TestCase {
	
	String htmlText = "<html><head><foo><bar></head><body><p>Paragraph" + 
		"<a href='http://grelber/' name=\"grelber\">Grelber Info</a>";
	
	public void testReadAll() throws Exception {
		Reader is = new StringReader(htmlText);
		ReadTag red = new ReadTag(is);
		List readTags = red.readTags();
		assertNotNull("list from readTags", readTags);
		assertTrue("any tags from readTags", 7 == readTags.size());
		Iterator tagsIterator = readTags.iterator();
		while (tagsIterator.hasNext()) {
			System.out.println(tagsIterator.next());
		}
	}
	
	public void testReadSome() throws Exception {
		Reader is = new StringReader(htmlText);
		ReadTag red = new ReadTag(is);
		red.setWantedTags(new String[] { "a", "foo" });
		List readTags = red.readTags();
		assertNotNull("list from readTags", readTags);
		assertTrue("any tags from readTags", 2 == readTags.size());
		Iterator tagsIterator = readTags.iterator();
		while (tagsIterator.hasNext()) {
			System.out.println(tagsIterator.next());
		}
	}
	
	public void testReadNone() throws Exception {
		Reader is = new StringReader(htmlText);
		ReadTag red = new ReadTag(is);
		red.setWantedTags(new String[] { });
		List readTags = red.readTags();
		assertNotNull("list from readTags", readTags);
		assertTrue("any tags from readTags", 0 == readTags.size());
		Iterator tagsIterator = readTags.iterator();
		while (tagsIterator.hasNext()) {
			System.out.println(tagsIterator.next());
		}
	}
	
	public void testReadDocType() throws Exception {
		Reader is = new StringReader("<?xml version='1.0'?>");
		ReadTag red = new ReadTag(is);
		List list = red.readTags();
		assertNotNull("list from readTags", list);
		assertTrue("any tags from readTags", 1 == list.size());
		Element el = (Element)list.get(0);
		assertEquals("list type", "?xml", el.getType());
	}
	
	public void testReadAttrs() throws Exception {
		Reader is = new StringReader(htmlText);
		ReadTag red = new ReadTag(is);
		red.setWantedTags(new String[] { "a" });
		List list = red.readTags();
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
