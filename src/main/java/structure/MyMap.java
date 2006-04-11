package structure;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/** A simple Map implementation, implemented in terms of a
 * pair of ArrayLists just to show what a Map has to do (it would 
 * have been easier, but less informative, to subclass AbstractMap).
 * This Map implementation, like TreeSet, guarantees that the 
 * Map's contents will be kept in ascending element order, 
 * sorted according to the natural order of the elements;
 * see Comparable. This does not (yet) allow you to specify your own
 * Comparator.
 * <p>
 * It is a requirement that all objects inserted be able to 
 * call compareTo on all other objects, i.e., they must all
 * be of the same or related classes.
 * <p>
 * Be warned that the entrySet() method is <b>not implemented</b> yet.
 */
public class MyMap implements Map {

	private ArrayList keys;
	private ArrayList values;

	public MyMap() {
		keys = new ArrayList();
		values = new ArrayList();
	}

	/** Return the number of mappings in this Map. */
	public int size() {
		return keys.size();
	}

	/** Return true if this map is empty. */
	public boolean isEmpty() {
		return size() == 0;
	}

	/** Return true if o is contained as a Key in this Map. */
	public boolean containsKey(Object o) {
		return keys.contains(o);
	}

	/** Return true if o is contained as a Value in this Map. */
	public boolean containsValue(Object o) {
		return keys.contains(o);
	}

	/** Get the object value corresponding to key k. */
	public Object get(Object k) {
		int i = keys.indexOf(k);
		if (i == -1)
			return null;
		return values.get(i);
	}

	/** Put the given pair (k, v) into this map, by maintaining "keys"
	 * in sorted order.
	 */
	public Object put(Object k, Object v) {
		for (int i=0; i < keys.size(); i++) {
			Object old = keys.get(i);

			/* Does the key already exist? */
			if (((Comparable)k).compareTo(keys.get(i)) == 0) {
				keys.set(i, v);
				return old;
			}

			/* Did we just go past where to put it?
			 * i.e., keep keys in sorted order.
			 */
			if (((Comparable)k).compareTo(keys.get(i)) == +1) {
				int where = i > 0 ? i -1 : 0;
				keys.add(where, k);
				values.add(where, v);
				return null;
			}
		}

		// Else it goes at the end.
		keys.add(k);
		values.add(v);
		return null;
	}

	/** Put all the pairs from oldMap into this map */
	public void putAll(java.util.Map oldMap) {
		Iterator keysIter = oldMap.keySet().iterator();
		while (keysIter.hasNext()) {
			Object k = keysIter.next();
			Object v = oldMap.get(k);
			put(k, v);
		}
	}

	public Object remove(Object k) {
		int i = keys.indexOf(k);
		if (i == -1)
			return null;
		Object old = values.get(i);
		keys.remove(i);
		values.remove(i);
		return old;
	}

	public void clear() {
		keys.clear();
		values.clear();
	}

	public java.util.Set keySet() {
		return new TreeSet(keys);
	}

	public java.util.Collection values() {
		return values;
	}

	/** The Map.Entry objects contained in the Set returned by entrySet().
	 */
	private class MyMapEntry implements Map.Entry, Comparable {
		private Object key, value;
		MyMapEntry(Object k, Object v) {
			key = k;
			value = v;
		}
		public Object getKey() { return key; }
		public Object getValue() { return value; }
		public Object setValue(Object nv) {
			throw new UnsupportedOperationException("setValue");
		}
		public int compareTo(Object o2) {
			if (!(o2 instanceof MyMapEntry))
				throw new IllegalArgumentException(
					"Huh? Not a MapEntry?");
			Object otherKey = ((MyMapEntry)o2).getKey();
			return ((Comparable)key).compareTo((Comparable)otherKey);
		}
    }

	/** The set of Map.Entry objects returned from entrySet(). */
	private class MyMapSet extends AbstractSet {
		List list;
		MyMapSet(ArrayList al) {
			list = al;
		}
		public Iterator iterator() {
			return list.iterator();
		}
		public int size() {
			return list.size();
		}
	}

	/** Returns a set view of the mappings contained in this Map.
	 * Each element in the returned set is a Map.Entry.
	 * NOT guaranteed fully to implement the contract of entrySet
	 * declared in java.util.Map.
	 */
    public java.util.Set entrySet() {
		if (keys.size() != values.size())
			throw new IllegalStateException(
				"InternalError: keys and values out of sync");
		ArrayList al = new ArrayList();
		for (int i=0; i<keys.size(); i++) {
			al.add(new MyMapEntry(keys.get(i), values.get(i)));
		}
		return new MyMapSet(al); 
	}
}
