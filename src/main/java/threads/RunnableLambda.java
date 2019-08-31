package threads;

// tag::main[]
public class RunnableLambda {
	public static void main(String[] args) {
		new Thread(() -> System.out.println("Hello from a thread")).start();
	}
}
// end::main[]
