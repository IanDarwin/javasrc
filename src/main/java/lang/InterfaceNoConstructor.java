package lang;

// Just to prove whether interfaces are allowed constructors or not

public interface InterfaceNoConstructor {

	public InterfaceNoConstructor() {	// EXPECT COMPILE ERROR
		// This would be a Constructor, would it not?
		// Same name as class, and no return type.
	}
}
