import java.util.*;

/** A simple Map implementation. The keyset is stored in a SortedSet,
 * and the values in a corresponding ArrayList.
 * STATUS: INCOMPLETE
 */
public class MyMap implements Map {
	private TreeSet keys;
	private ArrayList values;
	public MyMap() {
		keys = new TreeSet();
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
		int i = keys.indexof(k);
		if (i == -1)
			return null;
		return values.get(i);
	}

	/** Put the given pair (k, v) into this map */
    public Object put(Object k, Object v) {
		boolean wasNew = keys.put(k);
		int i = keys.indexOf(k);
		Object old = null;
		if (wasNew) {
			values.add(i+1, v);
		} else {
			old = values.get(i);
			values.set(i, v);
		}
		return old;
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
		keys.remove(k);
		values.remove(old);
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

    private class MyMapEntry implements Map.Entry {
		private Object key, value;
		MyMapEntry(Object k, Object v) {
			key = k;
			value = v;
		}
        public Object getKey() { return key; }
        public Object getValue() { return value; }
        public Object setValue(Object nv) {
			throw new UnsupportedOperationException("Bye now");
		}
    }
    public java.util.Set entrySet() {
		ArrayList al = new ArrayList();
		// XXX unfinished
		return new TreeSet(al); 
	}
}
