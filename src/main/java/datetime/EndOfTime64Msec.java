package datetime;

import java.util.Date;

/** When will a 64-bit timer based in mSec since 1970 come to the end of days?
 * Current answer: Sun Aug 17 02:12:55 EST 292278994
 * @author Ian Darwin
 */
public class EndOfTime64Msec {
	public static void main(String[] args) {
		// BEGIN ofTime
		Date endOfTime = new Date(Long.MAX_VALUE);
		System.out.println("Java8 time overflows on " + endOfTime);
		// END ofTime
	}
}
