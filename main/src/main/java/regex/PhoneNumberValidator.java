package regex;

import java.util.regex.Pattern;

public class PhoneNumberValidator {
	/* This seems to handle most any valid North American 10-digit number */
	public static final String PATTERN_STRING =
		"(\\+1)?[\\s-]?\\(?\\d{3}\\)?[\\s-]?\\d{3}[\\s-]?\\d{4}";

	// Convert to pattern, once only
	public static final Pattern PHONENUM_PATTERN =
		Pattern.compile(PATTERN_STRING);

	public static boolean valid(String num) {
		return PHONENUM_PATTERN.matcher(num).matches();
	}
}
