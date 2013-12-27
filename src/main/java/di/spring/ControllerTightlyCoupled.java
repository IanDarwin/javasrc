package di.spring;

import di.View;

// BEGIN main
public class ControllerTightlyCoupled {

	public static void main(String[] args) {
		Model m = new SimpleModel();
		View v = new ConsoleViewer();
		((ConsoleViewer)v).setModel(m);
		v.displayMessage();
	}
}
// END main
