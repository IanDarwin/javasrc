package numbers;

import java.text.NumberFormat;
import java.text.ParseException;

// tag::main[]
public class CompactFormatDemo {

	static final Number[] nums = {
		0, 1, 1.25, 1234, 12345, 123456.78, 123456789012L
	};
	static final String[] strs = {
		"1", "1.25", "1234", "12.345K", "1234556.78", "123456789012L"	
	};
	
	public static void main(String[] args) throws ParseException {
		NumberFormat cnf = NumberFormat.getCompactNumberInstance();
		System.out.println("Formatting:");
		for (Number n : nums) {
			cnf.setParseIntegerOnly(false);
			cnf.setMinimumFractionDigits(2);
			System.out.println(n + ": " + cnf.format(n));
		}
		System.out.println("Parsing:");
		for (String s : strs) {
			System.out.println(s + ": " + cnf.parse(s));
		}
	}

}
// end::main[]
