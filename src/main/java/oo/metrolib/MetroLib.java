package oo.game;

public class Game {
	private Room room = Rooms.firstRoom;

	public static void main(String[] args) {
		println("This is a trivial game demonstrating OO structures.");
		BufferedReader is = new BufferedReader(
			new InputStringReader(System.in));
		while ((cmd = parseCmd(is)) != Cmd.QUIT)
			play(cmd);
		}
	}

	int parseCmd(BufferedReader is) {
		String line = is.readLine();
		if (line == null)
			return Cmd.QUIT;
		if (line.equalsIgnoreCase("north")
			return Cmd.NORTH;
		if (line.equalsIgnoreCase("east")
			return Cmd.EAST;
		if (line.equalsIgnoreCase("south")
			return Cmd.SOUTH;
		if (line.equalsIgnoreCase("west")
			return Cmd.WEST;

		// more later...

		return Cmd.UNKNOWN;
	}

	void play(int cmd) {
		boolean entered = false, noWay = true;;
		switch(cmd) {
		case Cmd.UNKNOWN:
			println("Unknown command!"); break;
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

	void println(String s) {
		System.out.println(s);
	}
}
