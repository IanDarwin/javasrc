package oo.metrolib;

/** A Room is one location in the game.
 */
class Room {
	/** The exits from this Room */
	Room north, east, south, west;
	/** The message to display when entering this room */
	String entryMessage = "You are now in a room";
	/** The message (if any) to display when leaving */
	String exitMessage;

	/** Default Constructor
	 */
	Room() {

	}

	/** Construct a Room with all its information filled in
	 */
	public Room(String entryMessage, String exitMessage,
			Room north, Room east, Room south, Room west) {
		super();
		this.entryMessage = entryMessage;
		this.exitMessage = exitMessage;
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}

	/**
	 * Set up all the fields. Must be done after constructor time because
	 * the instances have references to each other but they're all allocated at once.
	 * If you switch to a constructor, the order in Rooms.java will matter!
	 * @param n
	 * @param e
	 * @param s
	 * @param w
	 * @param inMsg
	 * @param outMsg
	 */
	public void setAll(Room n, Room e, Room s, Room w,
		String inMsg, String outMsg) {
		north = n; east = e; south = s; west = w;
		entryMessage = inMsg;
		exitMessage = outMsg;
	}

	public String getEntryMessage() {
		return entryMessage;
	}
}
