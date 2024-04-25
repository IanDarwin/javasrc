package regex;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

class RETest {
	@Test
	void compile() {
		Pattern p = Pattern.compile("^|} .*");
		Matcher m = p.matcher("'''bold'''");
		assertNotNull(m);	
	}
}
