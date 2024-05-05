package strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class CreditCardValidateIsValidTest {

	String number;

	public void initCreditCardValidateIsValidTest(String number, Boolean expected) {
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
	
	public static List<Object[]> params() {
		return Arrays.asList(cardNumbers);
	}

	@MethodSource("params")
	@ParameterizedTest
	public void isValidCard(String number, Boolean expected) {
		initCreditCardValidateIsValidTest(number, expected);
		assertEquals(expected, CreditCardValidate.isValidCard(number));
	}
}
