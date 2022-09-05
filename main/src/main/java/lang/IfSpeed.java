package lang;

public class IfSpeed {
	public static void main(String[] args) {
		System.out.println(
		  speedAlert(
			Double.parseDouble(args[0]),
			Double.parseDouble(args[0])));
	}

	public static String speedAlert(
	  double vehicleSpeed,
	  double speedLimit) {
		if (vehicleSpeed > (2 * speedLimit)) {
			return  "Your car will be impounded!";
		} else if (vehicleSpeed > 1.25 * speedLimit) {
			return "This will be expensive!" ;
		} else {
			return "Away we go!";
		}
	}
}
