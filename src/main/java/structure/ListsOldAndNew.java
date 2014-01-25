package structure;

import java.util.*;

public class ListsOldAndNew {

	public void oldWay() {
	// BEGIN oldWay
    List myList = new ArrayList();
    myList.add("hello");
    myList.add("goodbye");

    // myList.add(new Date()); This would compile but cause failures later

    Iterator it = myList.iterator();
    while (it.hasNext()) {
            String s = (String)it.next();
            System.out.println(s);
    }
	// END oldWay
	}

	public void newWay() {
	// BEGIN newWay
    List myList = new ArrayList();
    myList.add("hello");
    myList.add("goodbye");

    // myList.add(new Date()); This would compile but cause failures later

    Iterator it = myList.iterator();
    while (it.hasNext()) {
            String s = (String)it.next();
            System.out.println(s);
    }
	// END newWay
	}
}
