package RE-apache;

import org.apache.regexp.*;

/* 
 * Reverse the order of two fields.
 * Input:
 * Smith, John
 * Adams, John Quincy
 * Output:
 * John Smith
 * John Quincy Adams
 */
public class REmatchTwoFields {
	public static void main(String[] args) throws RESyntaxException {
		String inputLine = "Adams, John Quincy";
		// Construct an RE with parens to "grab" both field1 and field2
		RE r = new RE("(.*), (.*)");
		if (!r.match(inputLine))
			throw new IllegalArgumentException("Bad input");
		System.out.println(r.getParen(2) + ' ' + r.getParen(1));
	}
}
