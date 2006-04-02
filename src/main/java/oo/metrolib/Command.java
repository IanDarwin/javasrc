package oo.metrolib;

public enum Cmd {
	HELP,
	NORTH,
	EAST,
	SOUTH,
	WEST,
	QUIT,
	LOOK,
	UNKNOWN;

	public static Cmd parseCmd(String line) {
		if (line == null || line.equals("q") ||
			line.equalsIgnoreCase("quit"))
			return QUIT;
		if (line.equals("n") || line.equalsIgnoreCase("north"))
			return NORTH;
		if (line.equals("e") || line.equalsIgnoreCase("east"))
			return EAST;
		if (line.equals("s") || line.equalsIgnoreCase("south"))
			return SOUTH;
		if (line.equals("w") || line.equalsIgnoreCase("west"))
			return WEST;
		if (line.equals("look"))
			return LOOK;
		if (line.equals("h") || line.equals("?") ||
			line.equalsIgnoreCase("help"))
			return HELP;

		// more later...

		return UNKNOWN;
	}
}
