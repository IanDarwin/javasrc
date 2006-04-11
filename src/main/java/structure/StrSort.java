package structure;

import java.util.Collections;
import java.util.Vector;

/**
 * StrSort demonstrates sorting of strings using Collections.sort.
 */
public class StrSort {
	/** The list of strings to be sorted */
	static public String rawStrings[] = {
		"Qwerty",
		"Ian",
		"de Raadt",
		"Java",
		"Gosling",
		"Alpha",
		"Zulu"
	};

	Vector<String> v = new Vector<String>();

	void load() {
		v = new Vector<String>();
		for (int i=0; i<rawStrings.length; i++)
			v.addElement(rawStrings[i]);
	}

	void dump(String title) {
		System.out.println("***** " + title + " *****");
		for (int i=0; i<v.size(); i++)
			System.out.println("v["+i+"]="+v.elementAt(i));
	}

	/** Simple main program to test the sorting */
	public static void main(String[] argv) {
		System.out.println("StrSort Demo in Java");
		StrSort s = new StrSort();
		s.load();
		s.dump("Before");
		Collections.sort(s.v);
		s.dump("After");
	}
}
