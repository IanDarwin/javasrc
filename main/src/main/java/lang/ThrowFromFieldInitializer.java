package lang;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * How you can throw an exception from a field initializer; note the throws
 * clause must appear on the constructor.
 * @author Dr. Heinz Kabutz, http://www.javaspecialists.eu/
 */
public class ThrowFromFieldInitializer implements AutoCloseable {

	private final ObjectInputStream in = 
			new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream("no.such.file")));

	public ThrowFromFieldInitializer() throws IOException {
	}

	public static void main(String[] args) throws IOException {
		new ThrowFromFieldInitializer().close();
	}
	
	public void close() throws IOException {
		in.close();
	}
}
