package structure;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * StrSortCase demonstrates sorting of strings using Collections.sort
 * AND ignoring case.
 */
public class StrSortCase {
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

	List<String> v;

	void load() {
		v = new ArrayList<String>();
		for (int i=0; i<rawStrings.length; i++)
			v.add(rawStrings[i]);
	}

	void dump(String title) {
		System.out.println("***** " + title + " *****");
		for (int i=0; i<v.size(); i++)
			System.out.println("v["+i+"]="+v.get(i));
	}

	/** Simple main program to test the sorting */
	public static void main(String[] argv) {
		// BEGIN main
		System.out.println("StrSort Demo Ignoring Case");
		StrSortCase s = new StrSortCase();
		s.load();
		s.dump("Before");
		Collections.sort(s.v, String.CASE_INSENSITIVE_ORDER);
		s.dump("After");
	}
}
