package numbers;


public class StringToDouble {

	//+
	public static void main(String[] argv) {
		String aNumber = argv[0];	// not argv[1]
		double result;
		try {
			result = Double.parseDouble(aNumber); 
		} catch(NumberFormatException exc) {
			System.out.println("Invalid number " + aNumber);
			return;
		}
		System.out.println("Number is " + result);
	}
	//-
}
