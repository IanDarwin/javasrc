package metrolib;

import java.io.*;

public class MetroLib {
	private Room room;

	MetroLib() {
		room = Rooms.getFirstRoom();
	}

	/** Main program, get the game going and run it. */
	public static void main(String[] args) throws IOException {
		println("This is a trivial game demonstrating OO structures.");
		BufferedReader is = new BufferedReader(
			new InputStreamReader(System.in));
		MetroLib game = new MetroLib();
		println("You can in general move north south east or west");
		println("");
		// Manually print out first room's message.
		println(game.room.entryMessage);

		// Main loop: run the game.
		int cmd;
		while ((cmd = game.parseCmd(is)) != Cmd.QUIT) {
			game.play(cmd);
		}
	}

	/** Read a line and delegate it to the parser */
	int parseCmd(BufferedReader is) throws IOException {
		String line = is.readLine();
		return Cmd.parseCmd(line);
	}

	void play(int cmd) {
		Room newRoom = null;
		boolean noWay = false;
		switch(cmd) {
		case Cmd.UNKNOWN:
			println("Unknown command!");
			break;
		case Cmd.NORTH:
			if (room.north != null) {
				newRoom = room.north;
			} else
				noWay = true;
			break;
		case Cmd.EAST:
			if (room.east != null) {
				newRoom = room.east;
			} else
				noWay = true;
			break;
		case Cmd.SOUTH:
			if (room.south != null) {
				newRoom = room.south;
			} else
				noWay = true;
			break;
		case Cmd.WEST:
			if (room.west != null) {
				newRoom = room.west;
			} else
				noWay = true;
			break;
		case Cmd.HELP:
			println("Sorry, help not written yet. I cannot help it");
			break;
		default:
			println("LOGIC ERROR: Unhandled case " + (char)cmd);
			break;
		}
		if (noWay) {
			println("I see no way to go in that direction");
			return;
		}
		if (newRoom != null) {
			if (room.exitMessage != null) {
				println(room.exitMessage);
			}
			// The magic happens: we change rooms.
			room = newRoom;
			println(room.entryMessage);
		}
	}

	static void println(String s) {
		System.out.println(s);
	}
}
