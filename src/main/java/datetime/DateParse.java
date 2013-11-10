package datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** Show some date parses */
public class DateParse {
	public static void main(String[] args) {

		String armisticeDate = "1914-11-11";
		LocalDate aLD = LocalDate.parse(armisticeDate);
		System.out.println("Date: " + aLD);
		
		String armisticeDateTime = "1914-11-11T11:11";
		LocalDateTime aLDT = LocalDateTime.parse(armisticeDateTime);
		System.out.println("Date/Time: " + aLDT);
	}
}
