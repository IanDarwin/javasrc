import java.util.*;

/** Demonstrate use of Arrays.sort()
 * @version $id$
 */
public class SortCollection {
	public static void main(String[] unused) {
		Vector v = new Vector();
		v.add("painful");
		v.add("mainly");
		v.add("gaining");
		v.add("raindrops");
		
		Collections.sort(v);
		for (int i=0; i<v.size(); i++)
			System.out.println(v.elementAt(i));
	}
}
