package email;

public class Alias {
	/** The name for the alias */
	protected String name;
	/** The email address for this alias */
	protected String address;

	public Alias(String n, String addr) {
		name = n;
		address = addr;
	}

	public String toString() {
		return name + " = " + address;
	}

	/** Get name */
	public String getName() {
		return name;
	}

	/** Set name */
	public void setName(String name) {
		this.name = name;
	}

	/** Get address */
	public String getAddress() {
		return address;
	}

	/** Set address */
	public void setAddress(String address) {
		this.address = address;
	}
}
