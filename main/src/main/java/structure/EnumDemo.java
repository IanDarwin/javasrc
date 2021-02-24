package structure;

public class EnumDemo {

	enum Color {
		RED, AMBER, GREEN;
	}

	public static void main(String[] args) {
		Color color = Color.RED;
		String line = getLine();
		if (line != null) {
			// valueOf is case-sensitive, so translate "user input" to upper case
			// since enum values are (by half a century of convention) in UPPER CASE.
			color = Color.valueOf(line.toUpperCase());
		}
		switch (color) {
		case RED:
			System.out.println("Stop at white line");
			break;
		case GREEN:
			System.out.println("Go through");
			break;
		case AMBER:
			if (driverIsAggressive()) {
				System.out.println("Honk! Speed up !!");
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
