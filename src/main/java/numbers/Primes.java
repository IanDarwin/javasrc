package numbers;

/* Compute prime numbers, after Knuth, Vol 1, Sec 1.3.2, Alg. "P".
 * Unlike Knuth, I don't build table formatting into
 * computational programs; output is one per line.
 * <p>
 * Note that there may be more efficient algorithms for finding primes.
 * Consult a good book on numerical algorithms.
 * @author Ian Darwin
 */
public class Primes {
	/** The default stopping point for primes */
	public static final long DEFAULT_STOP = 4294967295L;
	/** The first prime number */
	public static final int FP = 2;

	static int MAX = 10000;

	public static void main(String[] args) {
		long[] prime = new long[MAX];

		long stop = DEFAULT_STOP;
		if (args.length == 1) {
			stop = Long.parseLong(args[0]);
		}

		prime[1] = FP; 			// P1 (ignore prime[0])
		long n = FP+1; 			// odd candidates
		int j = 1;				// numberFound

		boolean isPrime = true;	// for 3

		do {

			if (isPrime) {
				if (j == MAX-1) {
					// Grow array dynamically if needed
					long[] np = new long[MAX * 2];
					System.arraycopy(prime, 0, np, 0, MAX);
					MAX *= 2;
					prime = np;
				}
				prime[++j] = n;	// P2
				isPrime = false;
			}
			n += 2;				// P4

			for (int k = 2; k <= j && k < MAX; k++) {	// P5, P6, P8
				long q = n / prime[k];
				long r = n % prime[k];
				if (r == 0) {			
					break;
				}
				if (q <= prime[k]) {		// P7
					isPrime = true;
					break;
				}
			}
			
		} while (n < stop);				// P3

		for (int i=1; i<=j; i++)
			System.out.println(prime[i]);
	}
}
