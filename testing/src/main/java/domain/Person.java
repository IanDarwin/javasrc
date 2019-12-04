package domain;

// tag::main[]
public class Person {

	int id;
	protected String firstName;
	protected String lastName;
	
	public Person() {
		// Must code it since we need 2-arg form.
	}
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return getFullName();
	}
	
	public String getFullName() {
		StringBuilder sb = new StringBuilder();
		if (firstName != null)
			sb.append(firstName).append(' ');
		if (lastName != null)
			sb.append(lastName);
		if (sb.length() == 0)
			sb.append("NO NAME");
		return sb.toString();
	}
}
// end::main[]
