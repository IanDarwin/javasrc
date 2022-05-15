package lang;

public class SwitchExpression {

	public static void main(String[] args) {
		var x = 12;
		String s = switch (x) {
			case 11 -> "eleven";
			case 12 -> "YOU WIN";
			default -> "I dunno!";
		};
		System.out.println(s);
	}
}
