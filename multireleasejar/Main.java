package mrjdemo;

// tag::main[]
public class Main {
	public static void main(String[] args) { 
		var type = new Data().getType();
		System.out.printf("Run with a Data object of type '%s'.\n", type);
	}
}
// end::main[]
