package java.util;

/** Crudely map Vector to ArrayList, for "compatibility",
 * that is, for use with ArrayList-based code on really ancient JDK.
 * Don't do that anymore; this is really olde code. Time to upgrade.
 */
public class ArrayList {
	Vector v;

	public ArrayList() {
		v = new Vector();
	}

	public boolean add(Object o) {
		v.addElement(o);
		return true;
	}

	public void add(int index, Object o) {
		v.insertElementAt(o, index);
	}

	public Object get(int i) {
		return v.elementAt(i);
	}

	public int size() {
		return v.size();
	}

	public Iterator iterator() {
		return new MyVectorIterator();
	}

	class MyVectorIterator implements Iterator {

		protected int index = 0;

		/** Default Constructor */
		public MyVectorIterator() {
		}

		/** 
		 * Tell if there are any more elements.
		 * @return true if not at the end, i.e., if next() will succeed.
		 * @return false if next() will throw an exception.
		 */
		public boolean hasNext() {
			return (index < v.size());
		}

		/** Returns the next element from the data */
		public Object next() {
			if (hasNext()) {
				return v.get(index++);
			}
			throw new IndexOutOfBoundsException("only " + v.size() + " elements");
		}

		/** Remove the object that next() just returned.
		 * An Iterator is not required to support this interface,
		 * and certainly don't!
		 */
		public void remove() {
			throw new UnsupportedOperationException(
				"This demo does not implement the remove method");
		}

	}
}
