import java.math.BigDecimal;
import java.util.Stack;

/** A trivial reverse-polish stack-based calculator for big numbers */
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

	Stack s = new Stack();

	public BigDecimal calculate(Object[] input) {
		BigDecimal tmp;
		for (int i = 0; i < input.length; i++) {
			Object o = input[i];
			if (o instanceof BigDecimal) {
				s.push(o);
			} else if (o instanceof String) {
				switch (((String)o).charAt(0)) {
				// + and * are commutative, order doesn't matter
				case '+':
					s.push(((BigDecimal)s.pop()).add((BigDecimal)s.pop()));
					break;
				case '*':
					s.push(((BigDecimal)s.pop()).multiply((BigDecimal)s.pop()));
					break;
				// - and /, order *does* matter
				case '-':
					tmp = (BigDecimal)s.pop();
					s.push(((BigDecimal)s.pop()).subtract(tmp));
					break;
				case '/':
					tmp = (BigDecimal)s.pop();
					s.push(((BigDecimal)s.pop()).divide(tmp,
						BigDecimal.ROUND_UP));
					break;
				default:
					throw new IllegalStateException("Unknown OPERATOR popped");
				}
			} else {
				throw new IllegalArgumentException("Syntax error in input");
			}
		}
		return (BigDecimal)s.pop();
	}
}
