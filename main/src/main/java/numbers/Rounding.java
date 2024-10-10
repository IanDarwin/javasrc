/** Compare with c/rounding.c */
void main() {
	/** This shows a rounding error */
	System.out.println(0.3 * 3);
	System.out.println(3.0 * 3);
	/** Printf %f hides the rounding error by rounding off(?) */
	System.out.printf("%f\n", 0.3 * 3);
	System.out.printf("%f\n", 3.0 * 3);
}

