/** When does the UNIX date get into trouble? */

public class Y2038 {
	public static void main(String[] a) {

		// This should yield 2038AD, the hour of doom for the
		// last remaining 32-bit UNIX systems (there will be
		// millions of 64-bit UNIXes by then).

		long expiry = 0x7FFFFFFFL * 1000;

		System.out.println("32-bit UNIX expires on " +
			Long.toHexString(expiry) + " or " +
			new java.util.Date(expiry));
		// Why doesn't it?

		// Try going from msec of current time into a Date
		long now = System.currentTimeMillis();
		System.out.println(
			"Passing " + Long.toHexString(now) + " --> " +
			new java.util.Date(now));
		
	}
}
