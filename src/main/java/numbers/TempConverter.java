import java.text.*;

/* Print a table of fahrenheit and celsius temperatures 
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class TempConverter {

	public static void main(String args[]) {
		TempConverter t = new TempConverter();
		t.start();
		for (int i=-40; i<=120; i+=10) {
			float c = (i-32)*(5f/9);
			t.print(i, c);
		}
		t.end();
	}

	protected void start() {
	}

	protected void print(float f, float c) {
		System.out.println(f + " f = " + c + " c.");
	}

	protected void end() {
	}
}
