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
// tag::main[]
public class SimpleCalcScanner {
	/** The Scanner */
	protected Scanner scan;

	/** The output */
	protected PrintWriter out = new PrintWriter(System.out, true);

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
				String token;
				switch(token = scan.next()) {
				case "+":
					// Found + operator, perform it immediately.
					push(pop() + pop());
					break;
				case "-":
					// Found - operator, perform it (order matters).
					tmp = pop();
					push(pop() - tmp);
					break;
				case "*":
					// Multiply is commutative
					push(pop() * pop());
					break;
				case "/":
					// Handle division carefully: order matters!
					tmp = pop();
					push(pop() / tmp);
					break;
				case "=":
					out.println(peek());
					break;
				default:
					out.println("What's this? " + token);
					break;
				}
			}
		}
	}

	void push(double val) {
		s.push(Double.valueOf(val));
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
// end::main[]
