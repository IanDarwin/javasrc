package com.darwinsys.util;

/** Just some quick-and-dirty time format.
 * Nothing to do with Apple's excellent multimedia format!
 */
public class QuickTimeFormat {
	//+
	/** Convert a long ("time_t") to seconds and thousandths. */
	public static String msToSecs(long t) {
		return t/1000 + "." + t%1000;
	}
	//-
}
