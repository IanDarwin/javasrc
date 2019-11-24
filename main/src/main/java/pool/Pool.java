package pool;

import java.util.*;

/** A reusable object pool.
 * Clients must provide an implementation of PoolFactory to
 * make instances of the objects being pooled.
 * @author Ian Darwin.
 */
public class Pool {

	/** The index of the next-available object in the Pool. */
	protected int IX = 0;
	/** The objects in the pool. */
	protected List<Object> pool;
	/** The objects which are in use within the pool. */
	protected BitSet inUse;
	/** The initial size of the pool */
	protected int initSize;
	/** The maximum size of the pool */
	protected int maxSize;
	/** The increment size of the pool */
	protected int incrSize;
	/** The PoolFactory to get more */
	protected PoolFactory factory;

	public Pool(PoolFactory fact, int init, int max, int incr) {
		factory = fact;
		initSize = init;
		maxSize = max;
		incrSize = incr;

		pool = new ArrayList<Object>(maxSize);
		inUse = new BitSet(maxSize);
		for (int i=0; i<initSize; i++) {
			pool.add(factory.getInstance());
		}
	}

	synchronized Object take() {
		// Take next one, mark in use.
		if (IX>=pool.size()) {
			// XXX last-minute sweep??
			for (int i=pool.size(); 
				pool.size()+i<incrSize && pool.size()+i<maxSize; i++) {

				pool.add(factory.getInstance());
			}
			System.out.println("Grew pool to " + pool.size());
		}
		Object o = pool.get(IX);
		inUse.set(IX);

		// Now bump "take" counter
		Object tmp;
		do {
			IX++;
			tmp = pool.get(IX);
		} while (IX < pool.size() && inUse.get(IX));

		// Return the one we first got.
		return o;
	}

	/** Release the Poolable. Set IX to lowest available. Mark unused. */
	synchronized void release(Object r) {
		int where = pool.indexOf(r);
		if (where < IX)
			IX = where;
		inUse.clear(where);
	}
}
