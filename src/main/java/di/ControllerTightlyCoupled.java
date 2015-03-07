package di;

import di.spring.ConsoleViewer;
import di.spring.Model;
import di.spring.SimpleModel;

// BEGIN main
public class ControllerTightlyCoupled {

	public static void main(String[] args) {
		Model m = new SimpleModel();
		ConsoleViewer v = new ConsoleViewer();
		v.setModel(m);
		v.displayMessage();
	}
}
// END main
