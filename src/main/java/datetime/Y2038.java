//+
/** When does the UNIX date get into trouble? */

public class Y2038 {
	public static void main(String a[]) {
		// This should yield 2038AD, the hour of doom for the
		// last remaining 32-bit UNIX systems (there will be
		// millions of 64-bit UNIXes by then).
		long expiry = Long.parseLong("7FFFFFFF",16)*1000;
		System.out.println("32-bit UNIX expires on " +
			Long.toHexString(expiry) + " or " +
			new java.util.Date(1000*0x7FFFFFFF));
		// Why doesn't it?

		// Try inverting:
		long now = System.currentTimeMillis();
		System.out.println(
			"Passing " + Long.toHexString(now) + " --> " +
			new java.util.Date(now));
		
	}
}
//-
