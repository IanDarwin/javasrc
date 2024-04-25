package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BigNumCalcTest {
	BigNumCalc calc;

	@BeforeEach
	void setUp() {
		calc = new BigNumCalc();
	}

	@Test
	void add() {
		Object[] testData = new Object[] {
			new BigDecimal(22),
			new BigDecimal(11),
			"+"
		};
		assertEquals(new BigDecimal(33),
			calc.calculate(testData));
	}

	@Test
	void subtract() {
		Object[] testData = new Object[] {
			new BigDecimal(22),
			new BigDecimal(11),
			"-"
		};
		assertEquals(new BigDecimal(11),
			calc.calculate(testData));
	}

	@Test
	void multipy() {
		Object[] testData = new Object[] {
			new BigDecimal(22),
			new BigDecimal(11),
			"*"
		};
		assertEquals(new BigDecimal(242),
			calc.calculate(testData));
	}

	@Test
	void divide() {
		Object[] testData = new Object[] {
			new BigDecimal(22),
			new BigDecimal(11),
			"/"
		};
		assertEquals(new BigDecimal(2),
			calc.calculate(testData));
	}
}
