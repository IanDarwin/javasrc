package structure50;

public class EnumDemo {

	enum Color {
		RED, BLUE, GREEN
	};

	public static void main(String[] args) {
		Color color = Color.RED;
		String line = getLine();
		if (line != null)
			color = Color.valueOf(line);
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

	private static String getLine() {
		return "GREEN";
	}

}
