package structure;

/** Output a list with { } around it, and commas between elements */
public class ListFormat {
	public static void main(String[] args) {
		String[] data = { "one", "two", "three", "four" };
		StringBuffer sb = new StringBuffer();
		for (String d : data) {
			if (sb.length() == 0)
				sb.append("{");
			else
				sb.append(", ");
			sb.append(d);
		}
		sb.append("}");
		System.out.println(sb);
	}
}
