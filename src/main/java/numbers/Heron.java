package numbers;

/** Compute the area of a triangle using Heron's Formula.
 * Code and values from Prof W. Kahan and Joseph D. Darcy.
 * See http://www.cs.berkeley.edu/~wkahan/JAVAhurt.pdf.
 * Derived from listing in Rick Grehan's Java Pro article (October 1999).
 * Simplified and reformatted by Ian Darwin.
 */
// BEGIN main
public class Heron {
	public static void main(String[] args) {
		// Sides for triangle in float
		float af, bf, cf;
		float sf, areaf;

		// Ditto in double
		double ad, bd, cd;
		double sd, aread;

		// Area of triangle in float
		af = 12345679.0f;
		bf = 12345678.0f;
		cf = 1.01233995f;

		sf = (af+bf+cf)/2.0f;
		areaf = (float)Math.sqrt(sf * (sf - af) * (sf - bf) * (sf - cf));
		System.out.println("Single precision: " + areaf);

		// Area of triangle in double
		ad = 12345679.0;
		bd = 12345678.0;
		cd = 1.01233995;

		sd = (ad+bd+cd)/2.0d;
		aread =        Math.sqrt(sd * (sd - ad) * (sd - bd) * (sd - cd));
		System.out.println("Double precision: " + aread);
	}
}
// END main
