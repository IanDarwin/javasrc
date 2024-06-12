package strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/** Soundex Test program. Knuth's original examples, and mine. */
public class SoundexTest {

	private String name, expected;

	public void initSoundexTest(String expected, String name) {
		this.expected = expected;
		this.name = name;
	}

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

	@MethodSource("getParams")
	@ParameterizedTest
	public void test(String expected, String name) {
		initSoundexTest(expected, name);
		assertEquals(expected, Soundex.soundex(name));
	}
}
