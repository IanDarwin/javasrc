package numbers;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class NumberToWordsConverterTest {

	private int input;
	private String expected;

	@Parameters
	public static Collection<Object[]> parameters() {

		Object[][] data = new Object[][] { 	
				{0,"zero"},
				{1, "one"},
				{2, "two"},
				{7,"seven"},
				{10, "ten" },
				{11,"eleven"},
				{12,"twelve"},
				{-2, "minus two"},
				{17, "seventeen"},
				{20, "twenty"},
				{21, "twenty one" },
				{23, "twenty three"},
				{34, "thirty four"},
				{40, "forty" },
				{-77, "minus seventy seven"},
				{100, "one hundred"},
				{101, "one hundred one"},
				{110, "one hundred ten"},
				{131, "one hundred thirty one"},
				{222, "two hundred twenty two"},
				{999, "nine hundred ninety nine"},
				// {1000, "one thousand"},
		};
		return Arrays.asList(data);
	}

	public NumberToWordsConverterTest(int input, String expected) {
		this.input = input;
		this.expected = expected;
	}

	@Test
	public void testConvert() {
		assertEquals(expected, NumberToWordsConverter.convert(input));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOutOfRange(){
		NumberToWordsConverter.convert(NumberToWordsConverter.MAX + 1);
	}
}


