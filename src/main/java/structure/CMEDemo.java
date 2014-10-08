package structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Shows how easy it is to get a CME (ConcurrentModificationException);
 * this would have to be re-written using an Iterator and calling
 * iterator.remove(s) instead of the way removal is attempted here.
 * @author Ian Darwin
 */
public class CMEDemo {

	public static void main(String[] args) {
		List<String> strings = 
			new ArrayList<>();
		strings.add("one");
		strings.add("two");
		strings.add("three");
		strings.add("four");

		System.out.println("Right way:");
		Iterator<String> it = strings.iterator();
		while (it.hasNext()) {
			String s = it.next();
			if (s.equals("two")) {
				it.remove();	// removes current element
			} else {
				System.out.println(s);
			}
		}
		
		System.out.println("Wrong way:");
		for (String s : strings) {
			if (s.equals("three")) {
				strings.remove(s);	// CME will happen here.
			} else {
				System.out.println(s);
			}
		}
	}
}
