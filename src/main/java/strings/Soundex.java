/**
Soundex - Implementation of the Soundex Algorithm as Described by Knuth

This class implements the soundex algorithm as described by Donald Knuth
in Volume 3 of <I>The Art of Computer Programming</I>.  The algorithm is
intended to hash words (in particular surnames) into a small space using a
simple model which approximates the sound of the word when spoken by an English
speaker.  Each word is reduced to a four character string, the first
character being an upper case letter and the remaining three being digits.

If there is no soundex code representation for a string then the value of
<I>soundex_nocode</I> is returned.  This is initially set to "Z000", but
you can optionally set this to null instead of the "unlikely" value "Z000"
(how unlikely this is depends on the data set being dealt with.)  Any 
String value can be assigned to soundex_nocode.

EXAMPLES

Knuth's examples of various names and the soundex codes they map to
are listed below:

  Euler, Ellery -> E460
  Gauss, Ghosh -> G200
  Hilbert, Heilbronn -> H416
  Knuth, Kant -> K530
  Lloyd, Ladd -> L300
  Lukasiewicz, Lissajous -> L222

LIMITATIONS

As the soundex algorithm was originally used a <B>long</B> time ago in the 
United States of America, it uses only the English alphabet and pronunciation.

As it is mapping a large space (arbitrary length strings) onto a small
space (single letter plus 3 digits) no inference can be made about the
similarity of two strings which end up with the same soundex code.  For 
example, both "Hilbert" and "Heilbronn" end up with a soundex code
of "H416".

@author
Perl implementation by Mike Stok (<stok@cybercom.net>) from the 
description given by Knuth.  Ian Phillips (<ian@pipex.net>) and Rich Pinder 
(<rpinder@hsc.usc.edu>) supplied ideas and spotted mistakes.
@author Ian Darwin, ian@darwinsys.com (Java Version)
@version $Id$
*/

public class Soundex {

	/** soundex_nocode indicates a string doesn't have a soundex code */
	public static String soundex_nocode = "Z000";

	/* Implements the mapping
	 * from: AEHIOUWYBFPVCGJKQSXZDTLMNR
	 * to:   00000000111122222222334556
	 */
	public static final char map[] = {
		'0','1','2','3','0','1','2','0','0','2','2','4','5',
		'5','0','1','2','6','2','3','0','1','0','2','0','2'
	};

	public static String soundex(String s) {
		String t = s.toUpperCase();
		StringBuffer res = new StringBuffer();

		for (int i=0; i<t.length() && i < 4; i++) {
			char c = t.charAt(i);
			if (!(c>='A' && c<='Z')) 	// only handles ASCII letters
				continue;
			if (i==0)
				res.append(c);
			else {
				char m = map[c-'A'];
				if (m != '0')
					res.append(m);
			}
		}
		if (res.length() == 0)
			return soundex_nocode;
		for (int i=res.length(); i<4; i++)
			res.append('0');
		return res.toString();
	}

	/** Test program. Knuth's original examples, and mine. */
	public static void main(String[] av) {
		test("Euler, Ellery", "E460");
		test("Gauss, Ghosh", "G200");
		test("Hilbert, Heilbronn", "H416");
		test("Knuth, Kant", "K530");
		test("Lloyd, Ladd", "L300");
		test("Lukasiewicz, Lissajous", "L222");
		test("Darwin", "D612");
		test("Darwent", "D612");
		test("Derwin", "D612");
	}

	/** test function */
	public static void test(String name, String value) {
		String res;
		System.out.print(name + "\t=> " + (res=soundex(name)) +
			"; expected " + value);
		System.out.println(res.equals(value) ? " OK" : " BAD");
	}
}

