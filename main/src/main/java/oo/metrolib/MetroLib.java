package oo.metrolib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MetroLib {
	private Room room;

	MetroLib() {
		room = Rooms.getFirstRoom();
	}

	/** Main program, get the game going and run it. */
	public static void main(String[] args) throws IOException {
		println("This is a trivial game demonstrating OO structures.");
		println("I am the last descendant of a long-lost game of great grandeur.");

		BufferedReader is = new BufferedReader(
			new InputStreamReader(System.in));

		MetroLib game = new MetroLib();
		println("Welcome! You can in general move north south east or west");
		println("");
		// Manually print out first room's message.
		println(game.room.entryMessage);

		// Main loop: run the game.
		Command cmd;
		while ((cmd = Command.parseCmd(is.readLine())) != Command.QUIT) {
			game.play(cmd);
		}
		println("Farewell, voyager");
	}

	/** Read a line and delegate it to the parser */
	Command parseCmd(BufferedReader is) throws IOException {
		String line = is.readLine();
		return Command.parseCmd(line);
	}

	void play(Command cmd) {
		Room newRoom = null;
		boolean noWay = false;
		switch(cmd) {
		case UNKNOWN:
			println("Unknown command!");
			break;
		case NORTH:
			if (room.north != null) {
				newRoom = room.north;
			} else
				noWay = true;
			break;
		case EAST:
			if (room.east != null) {
				newRoom = room.east;
			} else
				noWay = true;
			break;
		case SOUTH:
			if (room.south != null) {
				newRoom = room.south;
			} else
				noWay = true;
			break;
		case WEST:
			if (room.west != null) {
				newRoom = room.west;
			} else
				noWay = true;
			break;
		case LOOK:
			println(room.entryMessage);
			return;
		case HELP:
			println("Sorry, help not written yet. I cannot help it");
			return;
		default:
			println("LOGIC ERROR: Unhandled case " + cmd);
			return;
		}
		if (noWay) {
			println("I see no way to go in the direction " + cmd);
			return;
		}
		if (newRoom != null) {
			if (room.exitMessage != null) {
				println(room.exitMessage);
			}
			// The magic happens: we change rooms.
			room = newRoom;
			println(room.getEntryMessage());
		}
	}

	static void println(String s) {
		System.out.println(s);
	}
}
