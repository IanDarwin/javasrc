package lang;

import java.io.*;

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

	public class MyDataException extends RuntimeException {
		public MyDataException(String s, Throwable t) {
			super(s, t);
		}
	}
}
