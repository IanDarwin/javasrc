package metrolib;

/** This is the rooms data structure.
 * In a real game this would be generated from a database!
 */
public class Rooms {
	static Room lobby;
	static Room mainHall;
	static Room hallOfKings;

	static {
		System.out.println("Building rooms...");

		// MUST init these all before setting them up.
		lobby = new Room();
		mainHall = new Room();
		hallofKings = new Room();

		lobby.setAll(null, mainHall, null, null,
		"You are standing in the lobby. There is a door to the east", null);
		mainHall.setAll(null, hallOfKings, null, lobby,
		"You are in the Great Hall. There are doors to the east and west", null);
		hallOfKings.setAll(null, null, null, mainHall,
		"You are in the Hall of Kings. there is a door to the west", null);
	}

	public static Room getFirstRoom() {
		return lobby;
	}
}

