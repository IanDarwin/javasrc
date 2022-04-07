package lang;

/**
 * Show array initialized two ways, printed two ways,
 * second using StringBuilder.
 */
public class ArrayStringBuilder {
	public static void main(String[] args) {
		int[] data1 = new int[5];
		data1[0] = 2001;
		data1[1] = 2022;
		data1[2] = 2100;
		data1[3] = 3001;
		for (int d : data1)
			System.out.println(d);

		int[] data2 = { 50, 42, 50, 61, 52};

		var sb = new StringBuilder();
		for (int d : data2)
			sb.append((char)d);
		System.out.println(sb);
	}
}
