package gc;

/**
 * Statics -- class to show how static (class) variables work.
 *
 * We try to keep a useCount variable, but this code will show
 * that freed objects are not necessarily reclaimed immediately,
 * so useCount will in some circumstances show a higher number of nodes 
 * than is actually allocated. To fix this for a real program, you need
 * only move the "gc()" and "sleep()" calls into getCount(),
 * since that's when it's needed.
 */
public class Statics {
	static int useCount;

	public int getCount() {
		return useCount;
	}

	/** Constructor-- just increment useCount */
	Statics() {
		++useCount;
	}

	/** Finalizer -- just decrement useCount */
	public void finalize() {
		--useCount;
	}

	public static void main(String[] a) {
		final int N = 4;
		for (int i=1; i<=N; i++) {
			System.out.println("There are Now " +
				new Statics().getCount() +
				" Statics objects");
			if (i%3 == 0) {
				System.gc();
				System.out.println("Called gc()!");
				try {
					Thread.sleep(100);	// let GC run??
				} catch (Exception e) {
				}
			}
		}
	}
}
