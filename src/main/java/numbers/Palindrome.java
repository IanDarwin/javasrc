/** Compute the Palindrome of a number by adding the number composed of
 * its digits in reverse order, until a Palindrome occurs.
 * e.g., 42->66 (42+24); 1951->5995 (1951+1591=3542; 3542+2453=5995).
 * <P>TODO:
 * <BR>Handle negative numbers.
 * <BR>Rewrite rev() without using Strings.
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$.
 */
public class Palindrome {
	public static void main(String argv[]) {
		for (int i=0; i<argv.length; i++)
		try {
			System.out.println(argv[i] + "->" + 
				tryNum(Long.parseLong(argv[i])));
		} catch (NumberFormatException e) {
			System.err.println(argv[i] + "->" + " TOO BIG");
		} 
	}

	static long tryNum(long num) {
		if (isPal(num))
			return num;
		System.out.println("Trying " + num);
		return tryNum(num + rev(num));
	}

	static boolean isPal(long num) {
		if (num >= 0 && num < 9)
			return true;
		String nn = Long.toString(num);
		int len = nn.length();
		for (int i=0; i<len/2; i++)
			if (nn.charAt(i) != nn.charAt(len - i - 1))
				return false;
		return true;
	}

	static long rev(long num) {
		String nn = Long.toString(num);
		StringBuffer nb = new StringBuffer();
		for (int i=nn.length(); i>0; i--)
			nb.append(nn.charAt(i-1));
		return Long.parseLong(nb.toString());
	}
}
