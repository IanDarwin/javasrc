import java.io.*;
import java.net.*;
import java.util.*;

/**
 * SimpleCalc -- simple calculator to show StringTokenizer
 *
 * @author	Ian Darwin, ian@darwinsys.com
 * @version	$Id$
 */
public class SimpleCalc {
	/** The StreamTokenizer */
	protected  StreamTokenizer tf;

	/** The variable name (not used in this version) */
	protected String variable;
	/** The operand stack */
	protected Stack s;

	/* Driver - main program */
	public static void main(String av[]) throws IOException {
		if (av.length == 0)
			new SimpleCalc(
				new InputStreamReader(System.in)).doCalc();
		else 
			for (int i=0; i<av.length; i++)
				new SimpleCalc(av[i]).doCalc();
	}

	/** Construct a SimpleCalc by name */
	public SimpleCalc(String fileName) throws IOException {
		this(new FileReader(fileName));
	}

	/** Construct a SimpleCalc from an existing Reader */
	public SimpleCalc(Reader rdr) throws IOException {
		tf = new StreamTokenizer(rdr);
		// Control the input character set:
		tf.slashSlashComments(true);	// treat "//" as comments
		tf.ordinaryChar('-');		// used for subtraction
		tf.ordinaryChar('/');	// used for division

		s = new Stack();
	}

	protected void doCalc() throws IOException {
		int iType;
		double tmp;

		while ((iType = tf.nextToken()) != tf.TT_EOF) {
			switch(iType) {
			case tf.TT_NUMBER: // Found a number, push value to stack
				push(tf.nval);
				break;
			case tf.TT_WORD:
				// Found a variable, save its name. Not used here. */
				variable = tf.sval;
				break;
			case '+':
				// Found + operator, perform it immediately.
				push(pop() + pop());
				break;
			case '-':
				// Found + operator, perform it (order matters).
				tmp = pop();
				push(pop() - tmp);
				break;
			case '*':
				// Multiply works OK
				push(pop() * pop());
				break;
			case '/':
				// Handle division carefully: order matters!
				tmp = pop();
				push(pop() / tmp);
				break;
			case '=':
				System.out.println(peek());
				break;
			default:
				System.out.println("What's this? iType = " + iType);
			}
		}
	}
	void push(double val) {
		s.push(new Double(val));
	}
	double pop() {
		return ((Double)s.pop()).doubleValue();
	}
	double peek() {
		return ((Double)s.peek()).doubleValue();
	}
	void clearStack() {
		s.removeAllElements();
	}
}
