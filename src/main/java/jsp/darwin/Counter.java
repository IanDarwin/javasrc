package darwin;

import java.io.*;

/** Simple "page counter" bean.
 * $Id$
 */
public class Counter implements Serializable {
	private int counter;

	/** A public no-argument constructor is required of a javabean. */
	public Counter() {
		counter = 1;
	}

	public int getCount() {
		return counter;
	}
	public void setCount(int c) {
		counter = c;
	}

	/** This method increments the counter. Must be called (in a scriptlet)
	 * explicitly at present since there's not way to call
	 * methods other than set/get methods in the JSP standard.
	 */
	public void incr() {
		++counter;
	}
}
