package unfinished;

/**
 * Integer versions of some java.lang.Math routines.
 */

public class IMath {

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
	public static long isqrt(long x) {
		if (x==0)
			return 0;
		if (x==1)
			return 1;
		long u, v, q, w;

	  u = x;
	  // q = 1<<16 * ((1<<16)-1);
	  q = Integer.MAX_VALUE;
	  System.out.println("huge q = " + q);

	  if (u > q)
		return 1<<16;

	  for (w = 1<<31; w > 0; w >>= 2) {
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
