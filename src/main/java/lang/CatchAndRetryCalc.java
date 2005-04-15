package lang;

import javax.swing.JOptionPane;

/**
 * Calculator that catches and retries Arithmetic errors
 * @author Ian Darwin
 */
public class CatchAndRetryCalc {

	public static void main(String[] args) {
		new CatchAndRetryCalc().calc();
	}

	/**
	 * This is the calculator.
	 */
	private void calc() {
		boolean done = false;
		int div = 0;
		do {
			try {
				int result = 42 / div++;
				JOptionPane.showMessageDialog(null, "That worked, result = " + result);
			} catch (ArithmeticException e) {
				JOptionPane.showMessageDialog(null, "That didn't work; " + e);
			}
		} while (!done);
	}
}
