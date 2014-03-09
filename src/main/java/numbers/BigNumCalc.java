package numbers;

import java.math.BigDecimal;
import java.util.Stack;

/** A trivial reverse-polish stack-based calculator for big numbers */
// BEGIN main
public class BigNumCalc {

	/** an array of Objects, simulating user input */
	public static Object[] testInput = {
		new BigDecimal("3419229223372036854775807.23343"),
		new BigDecimal("2.0"),
		"*",
	};

	public static void main(String[] args) {
		BigNumCalc calc = new BigNumCalc();
		System.out.println(calc.calculate(testInput));
	}

	/**
	 * Stack of numbers being used in the calculator.
	 */
	Stack<BigDecimal> stack = new Stack<>();

	/**
	 * Calculate a set of operands; the input is an Object array containing
	 * either BigDecimal objects (which may be pushed onto the Stack) and
	 * operators (which are operated on immediately).
	 * @param input
	 * @return
	 */
	public BigDecimal calculate(Object[] input) {
		BigDecimal tmp;
		for (int i = 0; i < input.length; i++) {
			Object o = input[i];
			if (o instanceof BigDecimal) {
				stack.push((BigDecimal) o);
			} else if (o instanceof String) {
				switch (((String)o).charAt(0)) {
				// + and * are commutative, order doesn't matter
				case '+':
					stack.push((stack.pop()).add(stack.pop()));
					break;
				case '*':
					stack.push((stack.pop()).multiply(stack.pop()));
					break;
				// - and /, order *does* matter
				case '-':
					tmp = (BigDecimal)stack.pop();
					stack.push((stack.pop()).subtract(tmp));
					break;
				case '/':
					tmp = stack.pop();
					stack.push((stack.pop()).divide(tmp,
						BigDecimal.ROUND_HALF_UP));
					break;
				default:
					throw new IllegalStateException("Unknown OPERATOR popped");
				}
			} else {
				throw new IllegalArgumentException("Syntax error in input");
			}
		}
		return stack.pop();
	}
}
// END main
