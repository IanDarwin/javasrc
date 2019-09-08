package di;

import di.spring.ConsoleViewer;
import di.spring.SimpleModel;

// tag::main[]
public class ControllerTightlyCoupled {

	public static void main(String[] args) {
		Model m = new SimpleModel();
		ConsoleViewer v = new ConsoleViewer();
		v.setModel(m);
		v.displayMessage();
	}
}
// end::main[]
