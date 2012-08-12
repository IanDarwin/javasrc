package lang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResource {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try (BufferedReader is = new BufferedReader(new FileReader("x.txt"))) {
			String line;
			while ((line = is.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println("Caught expected " + e);
		}
		// No finally needed, no close needed - it's all done automatically!
	}

}
