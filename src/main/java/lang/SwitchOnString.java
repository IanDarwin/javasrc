package lang;

/** Show the 'switch on string' feature of Java 7.
 * 
 * P L E A S E   R E A D   B E F O R E   C O M P L A I N I N G
 * This class absolutely requires Java SE 7+, so just add an exclusion rule
 * (Build Path -> Exclude) if you are living with a legacy version of Java SE.
 */
public class SwitchOnString {
	public static void main(String[] args) {
		String input = "red";
		switch(input) {
		case "red":
			System.out.println("Stop!");
			break;
		case "amber": case "yellow":
			System.out.println("Caution!");
			break;
		case "green":
			System.out.println("Go (placidly among the haste)");
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}
}
