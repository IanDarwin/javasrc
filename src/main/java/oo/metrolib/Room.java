package oo.games;

/** A Room is one location in the game.
 */
class Room {
	/** The exits from this Room */
	Room north, east, south, west;
	/** The message to display when entering this room */
	String entryMessage = "You are now in a room";
	/** The message (if any) to display when leaving */
	String exitMessage;
}
