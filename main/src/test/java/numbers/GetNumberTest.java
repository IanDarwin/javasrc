package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetNumberTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	Object[][] testData = {
			// Expected Value		String Input
			{ Integer.valueOf(0),	"0" },
			{ Integer.valueOf(1),	"1" },
			{ Integer.valueOf(-1),	"-1" },
			{ 42D, 					"42.0" },
			{ -3.14592857D, 		"-3.14592857D" },
			{ 42D, 					"42.0" },
			{ Double.NaN,			"0xDeadFish" },
	};

	@Test
	final void process() {
		for (Object[] oo : testData) {
			assertEquals(oo[0], GetNumber.process((String)oo[1]));
		}
	}

}
