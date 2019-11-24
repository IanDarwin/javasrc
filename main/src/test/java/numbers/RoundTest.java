package numbers;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RoundTest {
	double input;
	int expected;

	/**
	 * Constructor, only needed b/c  this is a Parameterized test
	 * @param input The double to be rounded
	 * @param expected The expected int value from rounding
	 */
	public RoundTest(double input, int expected) {
		super();
		this.input = input;
		this.expected = expected;
	}

	private static Object[][] data = {
			{0.1d, 0},
			{0.15000000000000002, 0},
			{0.2d, 0},
			{0.25d, 0},
			{0.3d, 0},
			{0.35d, 0},
			{0.39999999999999997, 0},
			{0.44999999999999996, 0},
			{0.49999999999999994, 0},
			{0.5499999999999999, 1},
			{0.6d, 1},
			{0.65d, 1},
			{0.7000000000000001, 1},
			{0.7500000000000001, 1},
			{0.8000000000000002, 1},
			{0.8500000000000002, 1},
			{0.9000000000000002, 1},
			{0.9500000000000003, 1},
	};
	
	@Parameters
	public static List<Object[]> getData() {
		return Arrays.asList(data);
	}

	@Test
	public void test() {
		assertEquals(expected, Round.round(input), 0.0000000000000001);
	}
}
