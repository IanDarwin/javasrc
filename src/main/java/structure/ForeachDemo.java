import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class ForeachDemo {
	static void iterate(Collection c) {
	    for (Object o : c)
 		 System.out.println(o);
	}
	public static void main(String args[]) {
		List<String> l = new ArrayList<String>();
		l.add("Toronto");
		l.add("Stockholm");
		iterate(l);
	}
}
