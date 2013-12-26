package reflection.cooklet;

// BEGIN main
public class DemoCooklet extends Cooklet {
	public void work() {
		System.out.println("I am busy baking cookies.");
	}
	public void terminate() {
		System.out.println("I am shutting down my ovens now.");
	}
}
// END main
