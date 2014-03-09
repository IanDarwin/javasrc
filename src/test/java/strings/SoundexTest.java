package strings;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/** Soundex Test program. Knuth's original examples, and mine. */
@RunWith(Parameterized.class)
public class SoundexTest {

	private String name, expected;
	
	public SoundexTest(String expected, String name) {
		super();
		this.expected = expected;
		this.name = name;
	}

	@Parameters
    public static List<Object[]> getParams() {
        return Arrays.asList(data);
    }
    private static Object[][] data = {
		{ "E460", "Euler, Ellery" },
		{ "G200", "Gauss, Ghosh" },
		{ "H416", "Hilbert, Heilbronn" },
		{ "K530", "Knuth, Kant" },
		{ "L300", "Lloyd, Ladd" },
		{ "L200", "Lukasiewicz, Lissajous" },
		{ "D650", "Darwin" },
		{ "D653", "Darwent" },
		{ "D650", "Derwin" },
		{ "D260", "Decker" },
	};
	
	@Test
	public void test() {
		assertEquals(expected, Soundex.soundex(name));
	}
}
