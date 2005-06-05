package numbers;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MarkPercentages {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NumberFormat fmt = DecimalFormat.getInstance();
		int MAX = args.length == 1 ? Integer.parseInt(args[0]) : 15;
		for (int i = 0; i <= MAX; i++) {
			System.out.println(i + " " + fmt.format(100.0 * i / MAX));			
		}
	}
}
