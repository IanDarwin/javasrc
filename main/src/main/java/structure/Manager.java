package structure;

public class Manager extends Employee {
	// name and personellNumber are inherited from Employee

	/** The name of this manager's Department */
	String managedDepartment;

	/** The manager's Administrative Assistant */
	Employee adminAssistant;

	/** Construct a Manager object */
	public Manager(String dept, Employee aa) {
		managedDepartment = dept;
		adminAssistant = aa;
	}

	/** Return this manager's department's name */
	public String getManagedDepartment() {
		return managedDepartment;
	}

	/** Update this manager's department's name */
	public void setManagedDepartment(String newName) {
		managedDepartment = newName;
	}
}
