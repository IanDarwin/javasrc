package oo;

public class SomeClass {
	public boolean equals(Object o2) {
		if (!(o2 instanceof SomeClass))
			return false;
		// compare fields; if any differ, return false.
		return true;
	}
}
