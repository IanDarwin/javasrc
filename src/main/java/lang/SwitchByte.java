public class SwitchByte {
	void voo() {
	byte x = bar();
	switch(x) {
	case 1000: System.exit(1000); break;
	default: System.exit(0); break;
	}
	}
	byte bar() {
		return 1000;
	}
}
