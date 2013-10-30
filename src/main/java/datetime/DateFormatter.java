package datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
	public static void main(String[] args) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		System.out.println(df.format(LocalDateTime.now()));
	}
}
