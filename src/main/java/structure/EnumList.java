/** Simple demo to print all the types of an enum. */
public class EnumList {
	public static void main(String[] args) {
		enum State { ON, OFF, UNKNOWN };
		for (State i : State.values()) {
			System.out.println(i);
		}
	}
}
