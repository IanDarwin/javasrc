import java.text.*;

public class RomanNumberSimple {
	public static void main(String[] args) {
		NumberFormat nf = new RomanNumberFormat();
		int year = Calendar.getInstance().getYear();
		System.out.println(year + " -> " + nf.format(year);
	}
}
