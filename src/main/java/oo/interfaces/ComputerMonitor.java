public class ComputerMonitor extends ComputerAsset implements PowerSwitchable {
	ComputerMonitor(int roomNumber) {
		super(roomNumber);
	}
	public void powerDown() {
		System.out.println("Dousing monitor at desk " + deskNumber);
		// send the code to the Monitor's X10 box to shut it off
	}
	public void powerUp() {
		System.out.println("Warming up monitor at desk " + deskNumber);
		// send the code to the Monitor's X10 box to turn it on
	}
}
