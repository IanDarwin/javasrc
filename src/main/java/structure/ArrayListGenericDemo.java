import java.util.*;

public class ArrayListGenericDemo {
	public static void main(String[] args) {
		ArrayList<String> data = new ArrayList();
		data.add("hello");
		data.add("goodbye");
		try {
			data.add(new Date());
			System.err.println("Didn't throw expected Exception");
		} catch (Exception ex) {
			System.out.print("Caught expected exception: " + ex);
		}
		Iterator it = data.iterator();
		while (it.hasNext()) {
			String s = it.next();	// no cast needed
			System.out.println("Found String " + s);
		}
	}
}
