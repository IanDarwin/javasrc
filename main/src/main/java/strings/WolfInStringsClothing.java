package strings;

/**
 * If this class could be compiled, Java security would be a myth.
 */
// tag::main[]
public class WolfInStringsClothing 
	extends java.lang.String {		//EXPECT COMPILE ERROR
	private static final long serialVersionUID = 1;

	public void setCharAt(int index, char newChar) {
		// The implementation of this method
		// would be left as an exercise for the reader.
		// Hint: compile this code exactly as is before bothering!
	}
}
// end::main[]
