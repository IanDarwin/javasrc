import java.math.*;

/**
 * Limitations of BigDecimal when dealing with exact integral values.
 * @author Pointed out in "Java in a Nutshell", Chapter 9, "Common Text Formats".
 */
public class BigDecimalLimitation {
	public static void main(String[] args) {

		BigDecimal bd = new BigDecimal(BigInteger.ONE);
		bd.divide(new BigDecimal(3.0));
		System.out.println(bd); // Should be 1/3 but will throw
	}
}
