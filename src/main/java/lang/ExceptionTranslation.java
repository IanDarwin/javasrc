package lang;

import java.io.*;

/**
 * Translate a checked exception into unchecked.
 * All the cool kids like Spring Framework do this.
 */
public class ExceptionTranslation {

	public String readTheFile(String f) {
			try (BufferedReader is = new BufferedReader(new FileReader(f))) {
					String line = is.readLine();
					return line;
			} catch (FileNotFoundException fnf) {
					throw new MyDataException("Could not open file " + f, fnf);
			} catch (IOException ex) {
					throw new MyDataException("Could not read file " + f, ex);
			}
	}

	/**
	 * MyDataException: Something wrong in data layer. 
	 * Extends RuntimeException so as to be unchecked
	 */
	public class MyDataException extends RuntimeException {
		public MyDataException(String s, Throwable t) {
			super(s, t);
		}
	}
}
