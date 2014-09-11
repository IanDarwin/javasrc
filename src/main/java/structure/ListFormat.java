package structure;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** Output a list with [ ] around it, and commas between elements,
 * so that the array "one" "two" would print as [ "one", "two" ]
 * Note that for simple cases this is valid JSON
 */
public class ListFormat {

	public static String format(Object[] data) {
		StringBuilder sb = new StringBuilder().append('[');
		for (Object d : data) {
			if (sb.length() > 1)
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
		if (input.length() > 0) {
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
		}
		return ret;
	}
}
