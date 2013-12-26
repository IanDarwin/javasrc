package numbers;

/**
 * Multiply two matrices.
 * Only defined for int: clone the code (or wait for Templates)
 * for long, float, and double.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class MatrixUse {
	public static void main(String[] argv) {
		// BEGIN main
		int x[][] = {
			{ 3, 2, 3 },
			{ 5, 9, 8 },
		};
		int y[][] = {
			{ 4, 7 },
			{ 9, 3 },
			{ 8, 1 },
		};
		int z[][] = Matrix.multiply(x, y);
		Matrix.mprint(x);
		Matrix.mprint(y);
		Matrix.mprint(z);
		// END main
	}
}
