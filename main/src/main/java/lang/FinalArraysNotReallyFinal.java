package lang;

/** 
 * Explores one limitation on the meaning of "final" with arrays.
 */
public class FinalArraysNotReallyFinal {

	final static public String[] data = {
		"Yes",
		"Maybe",
		"No"
	};
	
	public static void main(String[] args) {
		
		// This won't compile, since it is "data" that is final
		// data = new String[] { "Sure" };
		
		dump("Before");
		
		// This is allowed, since the "final" does not follow along to "data[1]"
		data[1] = "Undecided";
		
		dump("After");
		
	}

	/**
	 * Dump the array with a header
	 */
	static void dump(String header) {
		System.out.println(header);
		for (String s : data) {
			System.out.println("\t" + s);
		}
	}

}
