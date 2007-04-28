package structure;

/** Output a list with { } around it, and commas between elements,
 * so that the array "one" "two" would print as { one, two }
 */
public class ListFormat {

	public static String format(Object[] data) {
		StringBuffer sb = new StringBuffer();
		for (Object d : data) {
			if (sb.length() == 0)
				sb.append("{");
			else
				sb.append(", ");
			sb.append(d);
		}
		sb.append("}");
		return sb.toString();
	}
}
