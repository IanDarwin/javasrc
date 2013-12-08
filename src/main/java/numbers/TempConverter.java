package numbers;


/* Print a table of Fahrenheit and Celsius temperatures 
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class TempConverter {

	public static void main(String[] args) {
		TempConverter t = new TempConverter();
		t.start();
		t.data();
		t.end();
	}

	protected void start() {
	}

	protected void data() {
		for (int i=-40; i<=120; i+=10) {
			float c = (i-32)*(5f/9);
			print(i, c);
		}
	}

	protected void print(float f, float c) {
		System.out.println(f + " " + c);
	}

	protected void end() {
	}
}
