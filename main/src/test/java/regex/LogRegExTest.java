package regex;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogRegExTest {
	Pattern p;

	@BeforeEach
	void testCompile() {
		p = Pattern.compile(LogRegEx.LOG_ENTRY_PATTERN, Pattern.COMMENTS);
		assertNotNull(p);	
	}

	@Test
	void apache() {
		Matcher m = p.matcher(LogParseInfo.LOG_ENTRY_LINE);
		assertTrue(m.matches());
		assertTrue(m.groupCount() > LogParseInfo.MIN_FIELDS);
	}

	@Test
	void wildFly() {
		String entry =
		"31.13.103.20 - - [17/Dec/2019:00:05:46 -0500] darwinsys.com \"GET /file/ HTTP/1.1\" 200 4225";
		Matcher m = p.matcher(entry);
		assertTrue(m.matches());
		assertTrue(m.groupCount() > LogParseInfo.MIN_FIELDS);
	}

	@Test
	void wildFly2() {
		String entry =
		"static.82.118.76.144.clients.your-server.de - - [17/Dec/2019:00:16:44 -0500] theories.darwinsys.com \"GET /ByCategory.web;jsessionid=3JtspVtEyY5cz9Z25KFFaU2Kr3ydneC5Xd6ioOV4.darwinsys?category=travel HTTP/1.1\" 200 12935";
		Matcher m = p.matcher(entry);
		assertTrue(m.matches());
	}
}
