package oo.game;

/** This is the rooms data structure.
 * In a real game this would be generated from a database!
 */
public class Rooms {
	private Room lobby = new Room(null, mainHall, null, null,
		"You are standing in the lobby. There is a door to the east", null);
	private Room mainHall = new Room(null, hallOfKings, null, lobby,
		"You are in the Great Hall. There are doors too the east and west", null);
	private Room hallOfKings = new Room(null, null, null, mainHall,
		"You are in the Hall of Kings. there is a door to the west", null);

	public Room getFirstRoom() {
		return lobby;
	}
}

