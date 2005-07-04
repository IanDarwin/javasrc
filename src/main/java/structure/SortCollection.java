package structure;

import java.util.*;

/** Demonstrate use of Arrays.sort()
 * @version $Id$
 */
public class SortCollection {
	public static void main(String[] unused) {
		List<String> l = new ArrayList<String>();
		l.add("painful");
		l.add("mainly");
		l.add("gaining");
		l.add("raindrops");
		
		Collections.sort(l);
		for (int i=0; i<l.size(); i++)
			System.out.println(l.get(i));
	}
}
