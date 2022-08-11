package lang;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class IfSpeedTest {

	@ParameterizedTest(name="Travelling at {0} in {1} zone should say {2}")
	@CsvSource({
		// speed, limit, expected message
		"101, 50, Your car will be impounded!",
		"75.1, 50, This will be expensive!",
		"50, 50, On we go!",
	})
	void test(double speed, double limit, String message) {
		System.out.printf("Speed %f Limit %f Message %s\n", speed, limit, message);
		assertEquals(message, IfSpeed.speedAlert(speed, limit), "Drive much?");
	}

}
