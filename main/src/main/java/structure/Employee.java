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

	public Employee(String name, int pnum) {
		this(name, pnum, 0);
	}

	public Employee(String name, long pnum, int age) {
		this.name = name;
		this.personellNumber = pnum;
		this.age = age;
	}

	public Employee toEmployee(String s) {
		String[] data = s.split("|");
		if (data.length != 3) {
			System.out.println( "Warning: Invalid String :" + s);
			return new Employee("Invalid", -1);
		}
		Employee e = new Employee(data[0], Long.parseLong(data[1]), Integer.parseInt(data[2]));
		return e;
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
