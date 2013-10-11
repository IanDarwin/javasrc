package numbers;

import junit.framework.TestCase;

public class GetNumberTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
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

	public final void testProcess() {
		for (Object[] oo : testData) {
			assertEquals(oo[0], GetNumber.process((String)oo[1]));
		}
	}

}
