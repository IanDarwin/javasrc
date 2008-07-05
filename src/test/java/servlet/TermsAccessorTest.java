package servlet;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class TermsAccessorTest {
	private static final String MY_IDENT = "My List of Terms";
	private static final String[] terms = { "fred", "whinger" };
	private static final String[] defns = {
		"see whinger",
		"see fred",
	};

	private TermsAccessor target;
	
	@Before
	public void init() throws IOException {
		assertEquals("raw data", terms.length, defns.length);
		
		StringBuffer sb = new StringBuffer(MY_IDENT).append('\n');
		for (int i = 0; i < terms.length; i++) {
			sb.append(terms[i]).append('\t').append(defns[i]).append('\n');
		}
		Reader rdr = new StringReader(sb.toString());
		target = new TermsAccessor(rdr);
		int targetListlength = 0;
		Iterator<Term> iter = target.iterator();
		while (iter.hasNext()) {
			iter.next();
			++targetListlength;
		}
		assertEquals("formatted data", terms.length, targetListlength );
	}
	
	@Test
	public final void testGetIdent() {
		assertEquals(MY_IDENT, target.getIdent());
	}

	@Test
	public final void testIterator() {
		final Iterator<Term> iterator = target.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			Term element = (Term) iterator.next();
			assertEquals(terms[i], element.term);
			assertEquals(defns[i], element.definition);
			++i;
		}
	}

}
