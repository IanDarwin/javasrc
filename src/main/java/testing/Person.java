package testing;

/** A simple class used to demonstrate unit testing. */
public class Person {
	protected String fullName;
	protected String firstName, lastName;

	/** Construct a Person using his/her first+last names. */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/** Get the person's full name */
	public String getFullName() {
		if (fullName != null)
			return fullName;
		return firstName + " " + lastName;
	}

	/** Simple test program. */
	public static void main(String[] argv) {
		Person p = new Person("Ian", "Darwin");
		String f = p.getFullName();
		if (!f.equals("Ian Darwin"))
			throw new IllegalStateException("Name concatenation broken");
		System.out.println("Fullname " + f + " looks good");
	}
}
