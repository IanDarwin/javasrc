import java.text.*;
public class TempConverterPrintf {
	public static void main(String unused[]) {
		DecimalFormat df = new DecimalFormat("##.###");
		for (int i=-40; i<=120; i+=10) {
			float c = (i-32)*(5f/9);
			System.out.println(i + " f = " + df.format(c) + " c.");
		}
	}
}
