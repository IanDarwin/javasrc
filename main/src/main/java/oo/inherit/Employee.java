package oo.inherit;

public class Employee extends Person {
	private short deptCode;
	private int staffNumber;

	public Employee(String firstName, String lastName, short dept, int number) {
		super(firstName, lastName);
		setDeptCode(dept);
		setStaffID(number);
	}

	public void setDeptCode(short dept) {
		deptCode = dept;
	}

	public void setStaffID(int number) {
		if (number < 1)
			throw new IllegalArgumentException("Invalid staff number " +
				number);
		staffNumber = number;
	}

	protected int getStaffNumber() {
		return staffNumber;
	}

	protected void setStaffNumber(int staffNumber) {
		this.staffNumber = staffNumber;
	}

	protected short getDeptCode() {
		return deptCode;
	}
}
