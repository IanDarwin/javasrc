package numbers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class NumberToWordsConverterTest {

	@ParameterizedTest
	@CsvSource(textBlock = """
        0,'zero'
        1, 'one'
        2, 'two'
        7,'seven'
        10, 'ten' 
        11,'eleven'
        12,'twelve'
        -2, 'minus two'
        17, 'seventeen'
        20, 'twenty'
        21, 'twenty one' 
        23, 'twenty three'
        34, 'thirty four'
        40, 'forty' 
        -77, 'minus seventy seven'
        100, 'one hundred'
        101, 'one hundred one'
        110, 'one hundred ten'
        131, 'one hundred thirty one'
        222, 'two hundred twenty two'
        999, 'nine hundred ninety nine'
        """)
		// More to test as the program grows...
        // 1000, 'one thousand'
	public void testConvert(int input, String expected) {
		assertEquals(expected, NumberToWordsConverter.convert(input));
	}

	@Test()
	public void testOutOfRange(){
		assertThrows(IllegalArgumentException.class,
				() -> NumberToWordsConverter.convert(NumberToWordsConverter.MAX + 1));
	}
}


