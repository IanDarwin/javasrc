import java.util.*;

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

	Vector v = new Vector();

	void load() {
		v = new Vector();
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
		//+
		System.out.println("StrSort Demo Ignoring Case");
		StrSortCase s = new StrSortCase();
		s.load();
		s.dump("Before");
		Collections.sort(s.v, String.CASE_INSENSITIVE_ORDER);
		s.dump("After");
	}
}
