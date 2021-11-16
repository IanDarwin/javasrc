public class BS {
	static int[] d = { 1, 2, 3, 5, 6, 10, 100, 222 };

	public static void main(String[] args) { 
		int i = 0;

		int len = d.length;
		System.out.println(search(7));
	}

	static int search(int target) {
		return search(target, 0);
	}

	static int search(int target, int start) {
		int i = d.length / 2;
		do {
			if (d[i] == 7) return i;
			if (d[i] < 7) return search(7, i);
			if (d[i] > 7) return search(7, i);
			return -i;
		} while (true);
	}
}

