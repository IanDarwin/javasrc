package functional;

// tag::main[]
public class ReferencesDemo3 {

	interface FunInterface {
		void process(int i, String j, char c, double d);
	}
	
	public static void work(int i, String j, char c, double d){
		System.out.println("In work " + c);
	}
	
	public static void main(String[] args) {
		FunInterface sample = ReferencesDemo3::work;
		sample.process(42, "Hello", '\u263A', Math.PI);
		System.out.println("My process method is " + sample);
	}
}
// end::main[]
