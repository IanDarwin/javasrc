import java.math.BigDecimal;
import java.util.Stack;

/** A trivial reverse-polish stack-based calculator for big numbers */
public class BigNumCalc {

	/** an array of Objects, simulating user input */
	public static Object[] input = {
		new BigDecimal("3419229223372036854775807.23343"),
		new BigDecimal("2.0"),
		"*",
		"=",
	};

	public static void main(String[] args) {
		Stack s = new Stack();
		for (int i = 0; i < input.length; i++) {
			Object o = input[i];
			if (o instanceof BigDecimal)
				s.push(o);
			else if (o instanceof String) {
				switch (((String)o).charAt(0)) {
				case '+':
					s.push(((BigDecimal)s.pop()).add((BigDecimal)s.pop()));
					break;
				case '-':
					s.push(((BigDecimal)s.pop()).subtract((BigDecimal)s.pop()));
					break;
				case '*':
					s.push(((BigDecimal)s.pop()).multiply((BigDecimal)s.pop()));
					break;
				case '/':
					s.push(((BigDecimal)s.pop()).divide((BigDecimal)s.pop(),
						BigDecimal.ROUND_UP));
					break;
				case '=':
					System.out.println(s.pop());
					break;
				default:
					throw new IllegalStateException("Unknown OPERATOR popped");
				}
			} else {
				throw new IllegalStateException("Syntax error in input");
			}
		}
	}
}
