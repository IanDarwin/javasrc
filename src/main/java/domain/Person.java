package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

// BEGIN main
@Entity
public class Person {

	int id;
	protected String firstName;
	protected String lastName;
	
	public Person() {
		// required by JPA; must code it since we need 2-arg form.
	}
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Id @GeneratedValue(strategy=GenerationType.AUTO, generator="my_poid_gen")
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

	@Column(name="surname")
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
	
	@Transient /* synthetic: cannot be used in JPA queries. */
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
// END main
