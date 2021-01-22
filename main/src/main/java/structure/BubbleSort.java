package structure;

/**
 * BubbleSort is widely known to be sub-optimal, and is usually reserved
 * for pedagogic use, as per this otherwise-useless example.
 */
public class BubbleSort {

	static final int d[] = { 24, 1, -5, 20, 50, 200, 11, 9, 7, 5, 3 };

	public static void main(String[] args) {
		boolean sorted = false;
		while (!sorted) {
			sorted = true;
			dump();
			for (int i = 0; i < d.length - 1; i++) {
				if (d[i] > d[i+1]) {
					sorted = false;
					int tmp = d[i];
					d[i] = d[i+1];
					d[i+1] = tmp;
				}
			}
		}
	}
	static void dump() {
		for (int i : d) {
			System.out.print(i);
			System.out.print(' ');
		}
		System.out.println();
	}
}
