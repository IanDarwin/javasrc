package structure;

public class EnumDemo {

	enum Color {
		RED, AMBER, GREEN;
	};

	public static void main(String[] args) {
		Color color = Color.RED;
		String line = getLine();
		if (line != null) {
			// valueOf is case-sensitive, so translate "user input" to upper case
			// since enum values are (by 30 years of convention) in UPPER CASE.
			color = Color.valueOf(line.toUpperCase());
		}
		switch (color) {
		case RED:
			System.out.println("STOP");
			break;
		case GREEN:
			System.out.println("GO");
			break;
		case AMBER:
			if (driverIsCrazy()) {
				System.out.println("Speed up like mad!");
			} else {
				System.out.println("Clear the intersection");
			}
			break;
		}
	}

	private static boolean driverIsCrazy() {
		return false;	// Not me, nope!
	}

	static String[] lines = { "Green", "amber", "RED" };
	static int i = 0;

	private static String getLine() {
		return lines[i++%3];
	}
}
