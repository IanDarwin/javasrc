package numbers;

/** My own implementation representing Complex Numbers. A Complex object is
 * immutable once created; the add, subtract and multiply routines
 * return newly-created Complex objects containing the results.
 *
 * @author Ian F. Darwin, inspired by David Flanagan.
 */
// tag::main[]
public record Complex(double r, double i) {

	/** Display the current Complex as a String, for use in
	 * println() and elsewhere.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder().append(r);
		if (i>0)
			sb.append('+');	// else append(i) appends - sign
		return sb.append(i).append('i').toString();
	}

	/** Return just the Real part */
	public double getReal() {
		return r;
	}
	/** Return just the Real part */
	public double getImaginary() {
		return i;
	}
	/** Return the magnitude of a complex number */
	public double magnitude() {
		return Math.sqrt(r*r + i*i);
	}

	/** Add another Complex to this one
	 */
	public Complex add(Complex other) {
		return add(this, other);
	}

	/** Add two Complexes
	 */
	public static Complex add(Complex c1, Complex c2) {
		return new Complex(c1.r+c2.r, c1.i+c2.i);
	}

	/** Subtract another Complex from this one
	 */
	public Complex subtract(Complex other) {
		return subtract(this, other);
	}

	/** Subtract two Complexes
	 */
	public static Complex subtract(Complex c1, Complex c2) {
		return new Complex(c1.r-c2.r, c1.i-c2.i);
	}

	/** Multiply this Complex times another one
	 */
	public Complex multiply(Complex other) {
		return multiply(this, other);
	}

	/** Multiply two Complexes
	 */
	public static Complex multiply(Complex c1, Complex c2) {
		return new Complex(c1.r*c2.r - c1.i*c2.i, c1.r*c2.i + c1.i*c2.r);
	}

	/** Divide c1 by c2.
	 * @author Gisbert Selke.
	 */
	public static Complex divide(Complex c1, Complex c2) {
		return new Complex(
			(c1.r*c2.r+c1.i*c2.i)/(c2.r*c2.r+c2.i*c2.i),
			(c1.i*c2.r-c1.r*c2.i)/(c2.r*c2.r+c2.i*c2.i));
	}
}
// end::main[]
