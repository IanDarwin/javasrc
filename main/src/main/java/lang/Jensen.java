package lang;

import java.util.function.Function;

/** Java 8 implementation of Jensen's Device 
 * See rosettacode.org/wiki/Jensen's_Device
 * The correct answer is 5.18...
 * @author Ian Darwin
 * @requires 8.0
 */
public class Jensen {
	static Integer i = 0;
	 
	static double sum(int o, int lo, int hi, Function<Double,Double> term) {
	 double tmp = 0;
	  for (i = lo; i <= hi; i++) {
		double od = o;
	    tmp += term.apply(od);
	  }
	  return tmp;
	}
	 
	public static void main(String[] args) {
		System.out.println(sum(i, 1, 100, eval -> 1d / i));
	}
}
