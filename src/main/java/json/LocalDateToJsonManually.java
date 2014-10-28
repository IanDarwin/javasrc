package json;

import java.time.LocalDate;

// BEGIN main
/** Convert an object to JSON, not using any JSON API. */
public class LocalDateToJsonManually {

	private static final String OPEN = "{";
	private static final String CLOSE = "}";
	
	public static void main(String[] args) {
		LocalDate dNow = LocalDate.now();
		System.out.println(toJson(dNow));
	}
	
	public static String toJson(LocalDate dNow) {
		StringBuilder sb = new StringBuilder();
		sb.append(OPEN).append("\n");
		sb.append(jsonize("year", dNow.getYear()));
		sb.append(jsonize("month", dNow.getMonth()));
		sb.append(jsonize("day", dNow.getDayOfMonth()));
		sb.append(CLOSE).append("\n");
		return sb.toString();
	}
	
	public static String jsonize(String key, Object value) {
		return String.format("\"%s\": \"%s\",\n", key, value);
	}
}
// END main
