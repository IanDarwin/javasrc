package structure;

import java.util.List;
import java.util.Vector;

/** From an example by Heinz Kabutz, showing use of an anonymous inner
 * class with a default constructor, to construct and initialize a List 
 * at the same time - saves coding and avoids race conditions in some circumstances.
 * @author Ian Darwin
 * @author original by Heinz, see http://www.javaspecialists.eu/
 */
public class InitializeList {

	public static void main(String[] args) {
		MySystem mySystem = new MySystem();
		mySystem.addPeople(new Vector<String>() {
			{
				add("Harry");
				add("Ron");
				add("Hermione");
			}
			private static final long serialVersionUID = -2081712664853752378L;
		});
		
		// Or, as Heinz would write it:
		mySystem.addPeople(new Vector<String>()
			{{ add("Harry"); add("Ron"); add("Hermione"); }});
	}
	
	private static class MySystem {
		public void addPeople(List<String> vector) {
			for (String n : vector) {
				System.out.println("Found " + n);
			}
		}
	}

}
