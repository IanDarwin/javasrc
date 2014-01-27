package datetimeold;

/** Convert longs (time_t in UNIX terminology) to seconds.
 */
public class LongToMSec {
	public static void main(String[] args) {
		System.out.println(msToSecs(1000));
		System.out.println(msToSecs(1100));
		System.out.println(msToSecs(1024));
	}

	/** Convert a long ("time_t") to seconds and milliseconds */
	public static String msToSecs(long t) {
		// The first attempt fails for the case "1024"
		//return t/1000 + "." + t%1000;

		return Double.toString(t/1000D);
	}
}

