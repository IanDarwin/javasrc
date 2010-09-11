package lang;

public class SwitchByte {
	void voo() {
		byte x = 42;
		switch(x) {
			case 127: System.out.println("OK"); break;
			// This class will not compile if the following line is uncommented,
			// because the switch type is byte, and does not get promoted to int.
			// case 128: System.out.println("Should Not Compile"); break;
			default: System.out.println("Should get here"); break;
		}
	}
}
