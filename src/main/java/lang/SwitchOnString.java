package lang;

/** Show the 'switch on string' feature of Java 7 */-
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
