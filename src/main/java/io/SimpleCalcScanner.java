package io;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;
import java.util.Stack;

/**
 * SimpleCalc -- simple calculator using 1.5 java.util.Scanner
 */
// BEGIN main
public class SimpleCalcScanner {
	/** The Scanner */
	protected  Scanner scan;

	/** The output */
	protected PrintWriter out = new PrintWriter(System.out);

	/** The variable name (not used in this version) */
	protected String variable;

	/** The operand stack; no operators are pushed, so it can be a stack of Double */
	protected Stack<Double> s = new Stack<>();

	/* Driver - main program */
	public static void main(String[] args) throws IOException {
		if (args.length == 0)
			new SimpleCalcScanner(
				new InputStreamReader(System.in)).doCalc();
		else 
			for (String arg : args) {
				new SimpleCalcScanner(arg).doCalc();
			}
	}

	/** Construct a SimpleCalcScanner by name */
	public SimpleCalcScanner(String fileName) throws IOException {
		this(new FileReader(fileName));
	}

	/** Construct a SimpleCalcScanner from an open Reader */
	public SimpleCalcScanner(Reader rdr) throws IOException {
		scan = new Scanner(rdr);
	}

	/** Construct a SimpleCalcScanner from a Reader and a PrintWriter */
	public SimpleCalcScanner(Reader rdr, PrintWriter pw) throws IOException {
		this(rdr);
		setWriter(pw);
	}

	/** Change the output to go to a new PrintWriter */
	public void setWriter(PrintWriter pw) {
		out = pw;
	}

	protected void doCalc() throws IOException {
		double tmp;

		while (scan.hasNext()) {
			if (scan.hasNextDouble()) {
				push(scan.nextDouble());
			} else {
				String token = scan.next();
				if (token.equals("+")) {
					// Found + operator, perform it immediately.
					push(pop() + pop());
				} else if (token.equals("-")) {
					// Found - operator, perform it (order matters).
					tmp = pop();
					push(pop() - tmp);
				} else if (token.equals("*")) {
					// Multiply is commutative
					push(pop() * pop());
				} else if (token.equals("/")) {
					// Handle division carefully: order matters!
					tmp = pop();
					push(pop() / tmp);
				} else if (token.equals("=")) {
					out.println(peek());
				} else {
					out.println("What's this? " + token);
				}
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
