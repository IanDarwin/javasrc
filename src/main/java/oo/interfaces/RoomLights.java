package oo.interfaces;

public class RoomLights extends BuildingLight implements PowerSwitchable {
	RoomLights(int roomNumber) {
		super(roomNumber);
	}
	public void powerDown() {
		System.out.println("Dousing lights in room " + room);
	}
	public void powerUp() {
		System.out.println("Lighting lights in room " + room);
	}
}
