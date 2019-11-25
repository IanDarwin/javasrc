package jaxwsclient;

// tag::main[]
// no imports!

/** Two-line client for Calc Service, complete code. */
public class TinyClientMain {
	public static void main(String[] args) {
		Calc client = new CalcService().getCalcPort();
		System.out.println(client.add(2, 2));
	}
}
// end::main[]
