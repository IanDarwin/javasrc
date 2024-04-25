package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class RoundTest {
	double input;
	int expected;

	/**
	 * Initialization method.
	 * @param input The double to be rounded
	 * @param expected The expected int value from rounding
	 */
	public void initRoundTest(double input, int expected) {
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
	
	public static List<Object[]> getData() {
		return Arrays.asList(data);
	}

	@MethodSource("getData")
	@ParameterizedTest
	public void test(double input, int expected) {
		initRoundTest(input, expected);
		assertEquals(expected, Round.round(input), 0.0000000000000001);
	}
}
