package structure;

import java.util.*;

/**
 * Demo of list processing pre- and post-Java 5.
 * Indentation is deliberately short, to fit the book formatting.
 */
public class ListsOldAndNew {

	@SuppressWarnings("unchecked")
	public void oldWay() {
	@SuppressWarnings("rawtypes")
	// BEGIN oldWay
	List myList = new ArrayList();
    myList.add("hello");
    myList.add("goodbye");

    // myList.add(new Date()); This would compile but cause failures later

    for (int i = 0; i < myList.size(); i++) {
            String s = (String)myList.get(i);
            System.out.println(s);
    }
	// END oldWay
	}

	public void newWay() {
	// BEGIN newWay
    List<String> myList = new ArrayList<>(); // Java 6: new ArrayList<String>();
    myList.add("hello");
    myList.add("goodbye");

    // myList.add(new Date()); This would not compile!

    for (String s : myList) {	// Look Ma, no downcast!
            System.out.println(s);
    }
	// END newWay
	}
}
