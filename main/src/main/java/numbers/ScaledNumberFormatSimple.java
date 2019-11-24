package numbers;

import com.darwinsys.util.ScaledNumberFormat;

/** A simple example of ScaleNumberFormat, from com.darwinsys.util
  */
public class ScaledNumberFormatSimple {
	public static void main(String[] args) {
		String cinput = "1.5K";
		long ninput = 10483892;
		long result;
		ScaledNumberFormat nf = new ScaledNumberFormat();

		result = ((Long)nf.parseObject(cinput, null)).longValue();
		System.out.println(cinput + " -> " + result);

		System.out.println(ninput + " -> " + nf.format(ninput));
	}
}
