package strings;

import java.util.StringTokenizer;

public class StringBufferCommaList {
	public static void main(String[] args) {
		StringTokenizer st = new StringTokenizer("Alpha Bravo Charlie");
		StringBuffer sb = new StringBuffer();
		while (st.hasMoreElements()) {
			sb.append(st.nextToken());
			if (st.hasMoreElements()) {
				sb.append(", ");
			}
		}
		System.out.println(sb);
	}
}
