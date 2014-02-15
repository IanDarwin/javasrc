package lang;

import java.util.Arrays;
import java.util.List;

public class Loops {

   String[] nerds = { "Bill", "Scott", "Larry", "Zuck" };
   List<String> nerdList = Arrays.asList(nerds);

   void loopUsingJava5ForEach() {
	   for (String n : nerds) {	// could also use nerdList here
		   System.out.println(n);
	   }
   }

   void loopUsingJava8orEach() {
	   nerdList.forEach(s -> System.out.println(s));
   }
}
