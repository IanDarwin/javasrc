package oo;

// BEGIN main
public class CopyConstructorDemo {
	public static void main(String[] args) {
		CopyConstructorDemo object1 = new CopyConstructorDemo(123, "Hello");
		CopyConstructorDemo object2 = new CopyConstructorDemo(object1);
		if (!object1.equals(object2)) {
			System.out.println("Something is terribly wrong...");
		}
		System.out.println("All done.");
	}

	private int number;
	private String name;

	/** Default constructor */
	public CopyConstructorDemo()  {
	}

	/** Normal constructor */
	public CopyConstructorDemo(int number, String name)  {
		this.number = number;
		this.name = name;
	}

	/** Copy Constructor */
	public CopyConstructorDemo(CopyConstructorDemo other)  {
		this.number = other.number;
		this.name = other.name;
	}
	// hashCode() and equals() not shown
	// END main

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CopyConstructorDemo other = (CopyConstructorDemo) obj;
		if (number != other.number)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
