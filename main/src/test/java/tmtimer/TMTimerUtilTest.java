package tmtimer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TMTimerUtilTest {
	Properties p;

	@BeforeEach
	void setUp() throws Exception {
		p = new Properties();
		p.setProperty("30,45,60", "0:30 0:45 1:00");
		p.setProperty("5m,6m,7m", "5:00 6:00 7:00");
	}

	@Test
	void parseProps() {
		Map<String,int[]> ret = TMTimerUtil.parseProps(p);
		int[] one = ret.get("30,45,60");
		assertEquals(3, one.length);
		assertEquals(30, one[0]);
		assertEquals(45, one[1]);
		assertEquals(60, one[2]);
	}

}
