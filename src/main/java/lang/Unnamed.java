/** Can a class in a package access a class in the unnamed package? */

package x;

import Unnamed1;

public class Unnamed {
	public static void main(String[] args) {
		System.out.println(new Unnamed1());
	}
}
