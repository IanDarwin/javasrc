package structure50;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class ForeachDemo {
	static void iterate(Collection<String> c) {
	    for (String s : c)
 		 System.out.println(s);
	}
	public static void main(String args[]) {
		List<String> l = new ArrayList<String>();
		l.add("Toronto");
		l.add("Stockholm");
		iterate(l);
	}
}
