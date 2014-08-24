package structure;

import java.util.*;

/** Output a list with [ ] around it, and commas between elements,
 * so that the array "one" "two" would print as [ "one", "two" ]
 * Note that for simle cases this is valid JSON
 */
public class ListFormat {

	public static String format(Object[] data) {
		StringBuffer sb = new StringBuffer();
		for (Object d : data) {
			if (sb.length() == 0)
				sb.append("[");
			else
				sb.append(", ");
			if (d instanceof String)
				sb.append('"').append(d).append('"');
			else
				sb.append(d);
		}
		sb.append("]");
		return sb.toString();
	}

	public static String format(List<?> data) {
		return format(data.toArray());
	}

	public static List<String> parse(String input) {
		List<String> ret = new ArrayList<>();
		input = input.substring(1, input.length() - 1);	// get rid of [ and ]
		StringTokenizer st = new StringTokenizer(input, ",");
		while (st.hasMoreTokens()) {
			String s = st.nextToken().trim();
			if (s.charAt(0) == '"') // string
				ret.add(s.substring(1, s.length() - 1)); // trim quotes
			else {					// number?
				ret.add(s);
			}
		}
		return ret;
	}
}
