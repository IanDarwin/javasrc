package metrolib;

import java.io.*;

public class MetroLib {
	private Room room;

	MetroLib() {
		room = Rooms.getFirstRoom();
	}

	public static void main(String[] args) throws IOException {
		println("This is a trivial game demonstrating OO structures.");
		println("You can move north south east or west");
		BufferedReader is = new BufferedReader(
			new InputStreamReader(System.in));
		MetroLib game = new MetroLib();
		int cmd;
		while ((cmd = game.parseCmd(is)) != Cmd.QUIT) {
			game.play(cmd);
		}
	}

	int parseCmd(BufferedReader is) throws IOException {
		String line = is.readLine();
		if (line == null)
			return Cmd.QUIT;
		if (line.equalsIgnoreCase("north"))
			return Cmd.NORTH;
		if (line.equalsIgnoreCase("east"))
			return Cmd.EAST;
		if (line.equalsIgnoreCase("south"))
			return Cmd.SOUTH;
		if (line.equalsIgnoreCase("west"))
			return Cmd.WEST;

		// more later...

		return Cmd.UNKNOWN;
	}

	void play(int cmd) {
		boolean entered = false, noWay = false;
println("IN PLAY: room=" + room);
		switch(cmd) {
		case Cmd.UNKNOWN:
			println("Unknown command!");
			break;
		case Cmd.NORTH:
			if (room.north != null) {
				room = room.north;
				entered = true;
			} else
				noWay = true;
			break;
		case Cmd.EAST:
			if (room.east != null) {
				room = room.east;
				entered = true;
			} else
				noWay = true;
			break;
		case Cmd.SOUTH:
			if (room.south != null) {
				room = room.south;
				entered = true;
			} else
				noWay = true;
			break;
		case Cmd.WEST:
			if (room.west != null) {
				room = room.west;
				entered = true;
			} else
				noWay = true;
			break;
		}
		if (entered && noWay)
			println("LOGIC ERROR");
		if (entered)
			println(room.entryMessage);
		if (noWay)
			println("I see no way to go in that direction");
	}

	static void println(String s) {
		System.out.println(s);
	}
}
