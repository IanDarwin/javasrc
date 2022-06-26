package numbers;

/**
 * Multiply two int matrices.
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class MatrixUse {
	public static void main(String[] argv) {
		// tag::main[]
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
		// end::main[]
	}
}
