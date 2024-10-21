package datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tutorial/Example of LocalDate and LocalDateTime parsing
 */
// tag::part1[]
public class DateParse {
	public static void main(String[] args) {

		String armisticeDateString = "1918-11-11";
		LocalDate armisticeDate = LocalDate.parse(armisticeDateString);
		System.out.println("Date: " + armisticeDate);
		
		String armisticeDateTimeString = "1918-11-11T11:00";
		LocalDateTime armisticeDateTime =
			LocalDateTime.parse(armisticeDateTimeString);
		System.out.println("Date/Time: " + armisticeDateTime);
		// end::part1[]
		
		// tag::part2[]
		// You can also parse with any DateTimeFormatter that matches your input
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM uuuu");
		String anotherDate = "27 Jan 2027";
		LocalDate random = LocalDate.parse(anotherDate, df);
		System.out.println(anotherDate + " parses as " + random);
		// end::part2[]
		
		System.out.println(armisticeDate + " formats as " + df.format(armisticeDate));
	}
}
