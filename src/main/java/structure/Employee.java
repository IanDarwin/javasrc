package structure;

/* Degenerate subset of Employee class, just enough to make
 * array demo code compile.
 */
public class Employee {
	protected String name;
	protected long personellNumber;

	Employee() {
		// Null constructor.
	}

	Employee(String name, int pnum) {
		this.name = name;
		this.personellNumber = pnum;
	}
	public String getName() {
		return name;
	}
}
