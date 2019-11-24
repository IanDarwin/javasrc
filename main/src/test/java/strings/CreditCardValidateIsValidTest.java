package strings;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CreditCardValidateIsValidTest {

	String number;

	public CreditCardValidateIsValidTest(String number, Boolean expected) {
		super();
		this.number = number;
		this.expected = expected;
	}

	Boolean expected;
	
	private static Object[][] cardNumbers = {
			{"4111 1111 1111 1111", true}, 
			{"1076 2112 8317 2708", true},
			{"3011 8231 2176 8115", false}, 
			{"4417 1234 5678 9112", false}, 
			{"4417 1234 5678 9113", true}, 
	};
	
	@Parameters
	public static List<Object[]> params() {
		return Arrays.asList(cardNumbers);
	}

	@Test
	public void testIsValidCard() {
		assertEquals(expected, CreditCardValidate.isValidCard(number));
	}

}
