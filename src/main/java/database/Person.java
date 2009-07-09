package database;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

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
	
	@Basic
	public String getLastName() {
		return lastName;
	}
	
	@Basic
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return getName();
	}
	
	@Transient /* synthetic: cannot be used in JPA queries. */
	public String getName() {
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
