package oo.metrolib;

/** The list of all valid commands, and code to find one from a String
 */
public enum Command {
	HELP,
	NORTH,
	EAST,
	SOUTH,
	WEST,
	QUIT,
	LOOK,
	UNKNOWN;

	@Override
	public String toString() {
		String s = super.toString();
		return s.charAt(0) + s.substring(1).toLowerCase();
	}

	public static Command parseCmd(String line) {
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
