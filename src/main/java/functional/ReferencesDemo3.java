package functional;

// BEGIN main
public class ReferencesDemo3 {

	interface FunInterface {
		void process(int i, String j, char c, double d);
	}
	
	public static void work(int i, String j, char c, double d){
		System.out.println("Moo");
	}
	
	public static void main(String[] args) {
		FunInterface sample = ReferencesDemo3::work;
		System.out.println("My process method is " + sample);
	}
}
// END main
