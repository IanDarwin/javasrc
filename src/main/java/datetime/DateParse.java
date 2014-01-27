package datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// BEGIN part1
/** Show some date parses */
public class DateParse {
	public static void main(String[] args) {

		String armisticeDate = "1914-11-11";
		LocalDate aLD = LocalDate.parse(armisticeDate);
		System.out.println("Date: " + aLD);
		
		String armisticeDateTime = "1914-11-11T11:11";
		LocalDateTime aLDT = LocalDateTime.parse(armisticeDateTime);
		System.out.println("Date/Time: " + aLDT);
		// END part1
		
		// BEGIN part2
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM uuuu");
		String anotherDate = "27 Jan 2011";
		LocalDate random = LocalDate.parse(anotherDate, df);
		System.out.println(anotherDate + " parses as " + random);
		// END part2
		
		System.out.println(aLD + " formats as " + df.format(aLD));
	}
}
