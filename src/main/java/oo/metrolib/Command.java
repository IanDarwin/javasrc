package metrolib;

public class Cmd {
	public final static int HELP = 'h';
	public final static int NORTH = 'n';
	public final static int EAST = 'e';
	public final static int SOUTH = 's';
	public final static int WEST = 'w';
	public final static int QUIT = 'q';
	public final static int UNKNOWN = '?';

	public static int parseCmd(String line) {
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

		if (line.equals("h") || line.equals("?") ||
			line.equalsIgnoreCase("help"))
			return HELP;

		// more later...

		return UNKNOWN;
	}
}
