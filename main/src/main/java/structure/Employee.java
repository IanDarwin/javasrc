package structure;

/* Degenerate subset of Employee class, just enough to make
 * array demo code compile.
 */
public class Employee {
	protected String name;
	protected long personellNumber;
	int age;

	Employee() {
		// Null constructor. Should eliminate and make fields final
	}

	Employee(String name, int pnum) {
		this(name, pnum, 0);
	}

	Employee(String name, int pnum, int age) {
		this.name = name;
		this.personellNumber = pnum;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public long getPersonnelNumber() {
		return personellNumber;
	}

	public int getAge() {
		return age;
	}
}
