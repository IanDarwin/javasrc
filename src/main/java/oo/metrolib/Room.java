package metrolib;

/** A Room is one location in the game.
 */
class Room {
	/** The exits from this Room */
	Room north, east, south, west;
	/** The message to display when entering this room */
	String entryMessage = "You are now in a room";
	/** The message (if any) to display when leaving */
	String exitMessage;

	public void setAll(Room n, Room e, Room s, Room w,
		String inMsg, String outMsg) {
		north = n; east = e; south = s; west = w;
		entryMessage = inMsg;
		exitMessage = outMsg;
	}
}
