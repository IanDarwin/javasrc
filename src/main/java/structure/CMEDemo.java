package structure;

import java.util.ArrayList;
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
		
		for (String s : strings) {
			if (s.equals("one")) {
				strings.remove(s);
			}
			System.out.println(s);
		}
	}
}
