import java.text.*;

/**
 * Roman Number class. Not localized, since Roman's a Dead Dead Language
 * and we don't display Roman Numbers differently in different Locales.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class RomanNumberFormat extends Format {

	Hashtable r2a;
	Hashtable a2r;
	final int NRN;

	public RomanNumberFormat() {
		r2a = new Hashtable();
		r2a.put("M", new Integer(1000));
		r2a.put("D", new Integer(500));
		r2a.put("C", new Integer(100));
		r2a.put("L", new Integer(50));
		r2a.put("X", new Integer(10));
		r2a.put("V", new Integer(5));
		r2a.put("I", new Integer(1));
		NRN = r2a.size;
		a2r = new Hashtable();
		a2r.put(new Integer(1000), "M");
		a2r.put(new Integer(500), "D");
		a2r.put(new Integer(100), "C");
		a2r.put(new Integer(50), "L");
		a2r.put(new Integer(10), "X");
		a2r.put(new Integer(5), "V");
		a2r.put(new Integer(1), "I");
	}

	public StringBuffer format(Object n, StringBuffer toAppTo, FieldPosition p) {
		System.out.println("In my format()!");
		toAppTo.append(n);
		return toAppTo;
	}

	/** Format a given long as a Roman Numeral */
	public String format(long n) {
		if (n < 0 || n > 4000)
			throw new IllegalArgumentException(n + " must be from 0 to 4000");
		StringBuffer sb = new StringBuffer();
		// FORMATTING
		String dig = Long.toString(n);
		for (int i=0; i<dig.length(); i++)
			char ch = dig.charAt(i);
			if (n > map[i].n) {
				sb.append(map[i].c);
				n /= map[i].n;
			}
		}
		return sb.toString();
	}

	/** Format a given double as a Roman Numeral; just truncate to a
	 * long, and call format(long).
	 */
	public String format(double n) {
		return format((long)n);
	}

	/** Parse a generic object, returning an Object */
	public Object parseObject(String what, ParsePosition where) {
		// TODO PARSING HERE
		return new Long(0);
	}

	/** Simple test case */
	public static void main(String argv[]) {
		RomanNumberFormat nf = new RomanNumberFormat();
		System.out.println("Test of " + nf);
		System.out.println("42->" + nf.format(42));
		System.out.println("XIV->" + nf.parseObject("XIV", null));
	}
}
