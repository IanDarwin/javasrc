package numbers;

import java.math.BigDecimal;

import junit.framework.TestCase;

public class BigNumCalcTest extends TestCase {
	BigNumCalc calc;

	public void setUp() {
		calc = new BigNumCalc();
	}

	public void testAdd() {
		Object[] testData = new Object[] {
			new BigDecimal(22),
			new BigDecimal(11),
			"+"
		};
		assertEquals(new BigDecimal(33),
			calc.calculate(testData));
	}

	public void testSubtract() {
		Object[] testData = new Object[] {
			new BigDecimal(22),
			new BigDecimal(11),
			"-"
		};
		assertEquals(new BigDecimal(11),
			calc.calculate(testData));
	}

	public void testMultipy() {
		Object[] testData = new Object[] {
			new BigDecimal(22),
			new BigDecimal(11),
			"*"
		};
		assertEquals(new BigDecimal(242),
			calc.calculate(testData));
	}

	public void testDivide() {
		Object[] testData = new Object[] {
			new BigDecimal(22),
			new BigDecimal(11),
			"/"
		};
		assertEquals(new BigDecimal(2),
			calc.calculate(testData));
	}
}
