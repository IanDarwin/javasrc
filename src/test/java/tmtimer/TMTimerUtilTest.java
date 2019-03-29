package tmtimer;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class TMTimerUtilTest {
	Properties p;
	@Before
	public void setUp() throws Exception {
		p = new Properties();
		p.setProperty("30,45,60", "0:30 0:45 1:00");
		p.setProperty("5m,6m,7m", "5:00 6:00 7:00");
	}

	@Test
	public void testParseProps() {
		Map<String,int[]> ret = TMTimerUtil.parseProps(p);
		int[] one = ret.get("30,45,60");
		assertEquals(3, one.length);
		assertEquals(30, one[0]);
		assertEquals(45, one[1]);
		assertEquals(60, one[2]);
	}

}
