package lang;

/** Can a class in a package access a class in the unnamed package? 
 * Answer: depends on the JDK. 1.3: yes. 1.4: no.
 * See http://java.sun.com/j2se/1.4/compatibility.html, item 8.
 */

package x;

import Unnamed1;	// EXPECT COMPILE ERROR (1.4 or later)

public class Unnamed {
	public static void main(String[] args) {
		System.out.println(new Unnamed1());
	}
}
