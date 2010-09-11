package regex;

import static org.junit.Assert.assertNotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RETest {
	@Test
	public void testCompile() {
		Pattern p = Pattern.compile("^|} .*");
		Matcher m = p.matcher("'''bold'''");
		assertNotNull(m);	
	}
}
