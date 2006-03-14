package structure50;

public class EnumDemo {

	enum Color {
		RED, BLUE, GREEN;
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
			System.out.println("You like dark apples");
			break;
		case GREEN:
			System.out.println("You like light apples");
			break;
		case BLUE:
			System.out.println("You like blue balloons");
			break;
		}
	}

	static String[] lines = { "Green", "blue", "RED" };
	static int i = 0;
	
	private static String getLine() {
		return lines[i++%3];
	}

}
