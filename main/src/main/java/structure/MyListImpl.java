package structure;

public class MyListImpl implements MyList {
	private Object[] data = new Object[255];
	private int index;

	public void add(Object o) {
		data[index++] = o;
	}

	public Object get(int i) {
		return data[i];
	}

	public int size() {
		return index;
	}
}

