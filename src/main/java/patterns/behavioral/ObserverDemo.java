import java.util.*;

/**
 * A simple demo of Observable->Observer
 *
 * @author Ian Darwin
 * @version $Id$
 */
public class ObservDemo extends Object {
	MyObserver observer;
	MyObservable model;
	public ObservDemo() {

		observer = new MyObserver();

		model = new MyObservable(); 
		model.addObserver(observer);

	}

	public static void main(String[] av) {
		ObservDemo me = new ObservDemo();
		me.demo();
	}

	public void demo() {
		model.changeSomething();
	}
}

/** The Observer normally maintains a view on the data */
class MyObserver implements Observer {
	/** For now, we just print the fact that we got notified. */
	public void update( Observable obs, Object x ) {
		System.out.println("update(" + obs + "," + x + ");");
	}
}

/** The Observable normally maintains the data */
class MyObservable extends Observable {
	public void changeSomething() {
		// Notify observers of change
		setChanged();
		notifyObservers();
	}
}
