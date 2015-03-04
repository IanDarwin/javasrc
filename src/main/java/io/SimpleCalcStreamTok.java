package io;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.Stack;

/**
 * SimpleCalc -- simple calculator to show StringTokenizer
 *
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class SimpleCalcStreamTok {
	/** The StreamTokenizer Input */
	protected  StreamTokenizer tf;
	/** The Output File */
	protected PrintWriter out = new PrintWriter(System.out, true);
	/** The variable name (not used in this version) */
	protected String variable;
	/** The operand stack */
	protected Stack<Double> s = new Stack<>();

	/* Driver - main program */
	public static void main(String[] av) throws IOException {
		if (av.length == 0)
			new SimpleCalcStreamTok(
				new InputStreamReader(System.in)).doCalc();
		else 
			for (int i=0; i<av.length; i++)
				new SimpleCalcStreamTok(av[i]).doCalc();
	}

	/** Construct by filename */
	public SimpleCalcStreamTok(String fileName) throws IOException {
		this(new FileReader(fileName));
	}

	/** Construct from an existing Reader */
	public SimpleCalcStreamTok(Reader rdr) throws IOException {
		tf = new StreamTokenizer(rdr);
		// Control the input character set:
		tf.slashSlashComments(true);	// treat "//" as comments
		tf.ordinaryChar('-');		// used for subtraction
		tf.ordinaryChar('/');	// used for division
	}

	/** Construct from a Reader and a PrintWriter
	 */
	public SimpleCalcStreamTok(Reader in, PrintWriter out) throws IOException {
		this(in);
		setOutput(out);
	}
	
	/**
	 * Change the output destination.
	 */
	public void setOutput(PrintWriter out) {
		this.out = out;
	}

	protected void doCalc() throws IOException {
		int iType;
		double tmp;

		while ((iType = tf.nextToken()) != StreamTokenizer.TT_EOF) {
			switch(iType) {
			case StreamTokenizer.TT_NUMBER: // Found a number, push value to stack
				push(tf.nval);
				break;
			case StreamTokenizer.TT_WORD:
				// Found a variable, save its name. Not used here.
				variable = tf.sval;
				break;
			case '+':
				// + operator is commutative.
				push(pop() + pop());
				break;
			case '-':
				// - operator: order matters.
				tmp = pop();
				push(pop() - tmp);
				break;
			case '*':
				// Multiply is commutative
				push(pop() * pop());
				break;
			case '/':
				// Handle division carefully: order matters!
				tmp = pop();
				push(pop() / tmp);
				break;
			case '=':
				out.println(peek());
				break;
			default:
				out.println("What's this? iType = " + iType);
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
// END main
