package structure;

/** Simple demo to print all the types of an enum. */
// BEGIN main
public class EnumList {
	enum State { 
		ON, OFF, UNKNOWN 
	}
	public static void main(String[] args) {
		for (State i : State.values()) {
			System.out.println(i);
		}
	}
}
// END main
