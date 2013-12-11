package numbers;


public class StringToDouble {

	// BEGIN main
	public static void main(String[] argv) {
		String aNumber = argv[0];	// not argv[1]
		double result;
		try {
			result = Double.parseDouble(aNumber); 
			System.out.println("Number is " + result);
		} catch(NumberFormatException exc) {
			System.out.println("Invalid number " + aNumber);
			return;
		}
	}
	// END main
}
