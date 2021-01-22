package lang;

public class SwitchYieldsValue {

	public static void main(String[] args) {
		var x = 12;
		String s = switch (x) {
		case 11:
			yield "eleven";
		case 12:
			yield "YOU WIN";
		default:
			yield "I dunno!";
		};
		System.out.println(s);
	}
}
