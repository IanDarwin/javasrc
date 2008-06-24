package dbm;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

public class TestDBM {
	
	@Ignore("Requires native code") @Test
	public void testOne() throws IOException {
		DBM d = new DBM("/tmp/mydb");
		System.out.println(d);
		d.store("today", new Date());
		d.store("here", System.getProperty("user.dir"));
		d.close();
	}
	
	@Ignore("Requires native code") @Test
	public void testTwoAndThree() throws Exception {
		DBM d2 = null;
		try { 
			d2 = new DBM("NoSuchDbAnyway"); 
			System.out.println(d2);
			fail("** ERROR ** failed to throw Exception");
		} catch (IllegalArgumentException e) {
			System.out.println("Correctly threw " + e);
		} finally {
			d2.close();
		}

		File tmp = File.createTempFile("foo", "bar");
		System.out.println("Now close, re-open, and fetch");
		d2 = new DBM(tmp.getAbsolutePath());
		System.out.println("d2 contains " + d2.fetch("here"));
		System.out.println("d2 contains " + d2.fetch("today"));
		d2.close();
		// This uses data loaded above:
		System.out.println("Now try iterating");
		DBM d3 = new DBM(tmp.getAbsolutePath());
		Object o;
		for (o = d3.firstkeyObject(); o != null; o = d3.nextkey(o)) {
			System.out.println("Key=\"" + o + "\"; " +
				"value=\"" + d3.fetch(o) + "\"");
		}
	}
}
