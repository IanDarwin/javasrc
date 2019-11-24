package oo.inherit;

public class Customer extends Person {
	
	private int customerNumber;

	public Customer(String firstName, String lastName, int number) {
		super(firstName, lastName);
		setCustomerNumber(number);
	}

	public void setCustomerNumber(int n) {
		if (n < 1)
			throw new IllegalArgumentException("Invalid customer number " + n);
		customerNumber = n;
	}

	protected int getCustomerNumber() {
		return customerNumber;
	}
}
