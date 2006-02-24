package structure;

/** A sample class used in some of the notes accompanying this code set.
 * N.B. This is NOT "best practices": should all be private fields,
 * and get/set methods.  (Done this way so examples are shorter).
 */
public class Person {
	private String firstName, lastName;
	public String address;
	public String phone;
	public String Country;
	
	public Person(String f, String l) {
		firstName = f;
		lastName = l;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getName() {
		return getFirstName() + ' ' + getLastName();
	}
	public String toString() {
		return getName();
	}
}
