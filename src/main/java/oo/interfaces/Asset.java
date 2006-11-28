package oo.interfaces;

/** Asset is the top level of every Asset */
public abstract class Asset extends Object {
	// No fields or methods; it's just the base of everything.

	/** make toString() abstract so all direct subclasses must implement it */
	public abstract String toString();
}

