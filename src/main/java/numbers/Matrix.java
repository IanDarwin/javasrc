/**
 * Multiply two matrices.
 * Only defined for int: clone the code (or wait for Templates)
 * for long, float, and double.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class Matrix {

	/* Matrix-multiply two arrays together.
	 * The arrays MUST be rectangular.
	 * @author Tom Christiansen & Nathan Torkington, Perl Cookbook version.
	 */
	public static int[][] multiply(int[][] m1, int[][] m2) {
		int m1rows = m1.length;
		int m1cols = m1[0].length;
		int m2rows = m2.length;
		int m2cols = m2[0].length;
		if (m1cols != m2rows)
			throw new IllegalArgumentException("matrices don't match: " + m1cols + " != " + m2rows);
		int[][] result = new int[m1rows][m2cols];

		// multiply
		for (int i=0; i<m1rows; i++)
			for (int j=0; j<m2cols; j++)
				for (int k=0; k<m1cols; k++)
				result[i][j] += m1[i][k] * m2[k][j];

		return result;
	}

	public static void mprint(int[][] a) {
		int rows = a.length;
		int cols = a[0].length;
		System.out.println("array["+rows+"]["+cols+"] = {");
		for (int i=0; i<rows; i++) {
			System.out.print("{");
			for (int j=0; j<cols; j++)
				System.out.print(" " + a[i][j] + ",");
			System.out.println("},");
		}
		System.out.println(":;");
	}
}
