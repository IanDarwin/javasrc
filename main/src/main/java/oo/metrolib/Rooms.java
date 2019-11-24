package oo.metrolib;

/** This is the rooms data structure.
 * In a real game this would be generated from a database!
 */
public class Rooms {
	static Room lobby;
	static Room mainHall;
	static Room hallOfKings;

	static {
		System.out.println("Building rooms...");

		// These must be allocated first, then set up (i.e., not created and
		// filled using a Constructor, as is more usual), because they
		// contain references to each other... The "obvious" way around
		// this, in BrokenCopyOfRooms, does result in NullPointerExceptions.
		lobby = new Room() {
			public String getEntryMessage() {
				return "I see you made it back to the lobby alive.";
			}
		};
		mainHall = new Room();
		hallOfKings = new Room();

		lobby.setAll(null, mainHall, null, null,
			"You are standing in the lobby. There is a door to the east",
			"Come visit this generic lobby again soon");
		mainHall.setAll(null, hallOfKings, null, lobby,
			"You are in the Great Hall. There are doors to the east and west",
			null);
		hallOfKings.setAll(null, null, null, mainHall,
			"You are in the Hall of The Mountain Kings. There is a door to the west", null);
	}

	public static Room getFirstRoom() {
		return lobby;
	}
}

