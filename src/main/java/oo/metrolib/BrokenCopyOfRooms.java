package oo.metrolib;

/** This is a borked copy of the rooms data structure; see the
 * comment in "Rooms.java" for discussion.
 * In a real game this would be generated from a database!
 */
public class BrokenCopyOfRooms {
	static Room lobby, mainHall, hallOfKings;

	static {
	lobby = new Room("You are standing in the lobby. There is a door to the east",
			"Come visit this generic lobby again soon",
			null, mainHall, null, null);
	mainHall = new Room(
			"You are in the Great Hall. There are doors to the east and west",
			null,
			null, hallOfKings, null, lobby);
	hallOfKings = new Room(
			"You are in the Hall of The Mountain Kings. There is a door to the west",
			null,
			null, null, null, mainHall);
	}

	public static Room getFirstRoom() {
		return lobby;
	}
}

