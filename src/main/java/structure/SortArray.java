import java.util.*;

/** Demonstrate use of Arrays.sort()
 * @version $id$
 */
public class SortArray {
	public static void main(String[] unused) {
		String[] strings = {
			"painful", 
			"mainly",
			"gaining",
			"raindrops"
		};
		Arrays.sort(strings);
		for (int i=0; i<strings.length; i++)
			System.out.println(strings[i]);
	}
}
