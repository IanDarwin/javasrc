package functional;

// BEGIN main
/** "Walk, don't run" */
public class ReferencesDemo {

	// Assume this is an existing method we don't want to rename
	public void walk() {
		System.out.println("ReferencesDemo.walk(): Stand-in run method called");
	}
	
	// This is our main processing method; it runs "walk" in a Thread
	public void doIt() {
		Runnable r = this::walk;
		new Thread(r).start();
	}
	
	// The usual simple main method to start things off
	public static void main(String[] args) {
		new ReferencesDemo().doIt();
	}
}
// END main
