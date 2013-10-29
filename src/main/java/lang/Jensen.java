package lang;

/** Java 8(!) implementation of Jensen's Device 
 * See rosettacode.org/wiki/Jensen's_Device
 * The correct answer is 5.18...
 * @author Ian Darwin
 * @requires 8.0
 */
public class Jensen {
	Integer val;
	 
	double sum(Object o, int lo, int hi, Object term) {
	 double tmp = 0;
	  for (val = lo; val <= hi; val++)
	    tmp += term();
	  return tmp;
	}
	 
	public static void main(String[] args) {
		System.out.println(sum(val, 1, 100, eval -> 1 / val));
	}
}
