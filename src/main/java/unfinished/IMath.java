/**
 * Integer versions of some java.lang.Math routines.
 */

public class IMath {

	protected static long two_to_the(long x)	 {
		return 1 << (x);
	}

	/**
	 * Compute the square root of an integer.
	 * <PRE>
	** ALGORITHM 650:
	** Efficient Square Root Implementation on the 68000
	** Kenneth C. Johnson
	** ACM Transactions on Mathematical Software,
	** Vol 13, No. 2, June 1987, Pages 138-151.
	**
	** The result is rounded to the nearest integer
	 * 
	 * From: rec@elf115.uu.net (Roger Critchlow)
	 * Newsgroups: comp.lang.c
	 * Subject: Re: Integer square root routine needed.
	 * Summary: ACM ALGORITHM 650
	 * Message-ID: <177@elf115.uu.net>
	 * Date: 2 Aug 89 18:33:12 GMT
	 * References: <7415@ecsvax.UUCP> <5392@ficc.uu.net> <7204@microsoft.UUCP>
	 * Organization: ELF, Sea Cliff, NY
	 * </PRE>
	 */
	public static long isqrt(int x) {
		if (x==0)
			return 0;
		if (x==1)
			return 1;
		long u, v, q, w;

	  u = x;
	  q = two_to_the(16) * (two_to_the(16)-1);

	  if (u > q)
		return two_to_the(16);

	  for (w = two_to_the(31); w > 0; w >>= 2) {
		v = (q - w) >> 1;
		if (u > v) {
		  u -= v;
		  q = v + w;
		} else
		  q = v;
	  }

	  q >>= 1;

	  return (u < q) ? q-1 : q;
	}
}
