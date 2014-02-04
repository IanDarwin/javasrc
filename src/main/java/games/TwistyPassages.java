package games;

import java.io.*;

/** Fake up the twisty passages part of Adenture(6) */
public class TwistyPassages {

	private static String[] list = {
		"You are in a maze of twisty little passages, all alike.",
		"You are in a maze of twisty little passages, all alike.",
		"You are in a maze of twisty little passages, all alike.",
		"You are in a maze of twisty little passages, all alike.",
		"You are in a maze of twisty little passages, all alike.",
		"You are in a maze of twisty little passages, all alike.",
		"You are in a maze of twisty little passages, all different.",
		"You are in a little maze of twisty passages, all different.",
		"You are in a twisty little maze of passages, all different.",
		"You are in a twisty maze of little passages, all different.",
		"You are in a little twisty maze of passages, all different.",
		"You are in a maze of little twisty passages, all different.",
	};
	
	private static String[] directions = {
		"east",
		"north",
		"south",
		"west",
		"up",
		"down"
	};

	public static void main(String[] args) throws IOException {

		BufferedReader is = new BufferedReader(
				new InputStreamReader(System.in));
		String inputLine;

		while ((inputLine = is.readLine()) != null) {
			if (!isVerb(inputLine)) {
				System.out.println("I don't understand that word");
			}
			System.out.println(list[(int)(Math.random() * list.length)]);
		}
		is.close();
	}

	private static boolean isVerb(String inputLine) {
		for (String dir : directions) {
			if (dir.startsWith(inputLine)) {
				return true;
			}
		}
		return false;
	}
}
