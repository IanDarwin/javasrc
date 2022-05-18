package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ParameterizedDemo5 {

	@ParameterizedTest(name = "{index} String {0} should give {1}")
	@CsvSource({
		"one, 1",
		"two, 2",
		"three, 3",
		"six, 6",
	})
	void testScoreNumbers(String word, int score) {
		assertEquals(score, Converter.convert(word));
	}

	static class Converter {
		public static int convert(String numberAsWords) {
			switch(numberAsWords.trim().toLowerCase()) {
				case "zero": return 0;
				case "one": return 1;
				case "two": return 2;
				case "three": return 3;
				case "four": return 4;
				case "five": return 5;
				case "size": return 6;
				case "zeven": return 7;
				case "ate": return 8;
				case "nine": return 9;
				case "ten": return 10;
				default: throw new UnsupportedOperationException("word numbers > 10 not supported");
			}
		}
	}
}
		
