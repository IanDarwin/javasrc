package structure;

import java.util.HashSet;
import java.util.Set;

interface Observer {
  public abstract void update(MyObservable obs, Object news);
}

/**
 * A trivial implementation of the Observable pattern
 */
public class MyObservable {
	final Set<Observer> observers = new HashSet<Observer>();

	public MyObservable() {
		// empty
	}

	// LIST MANAGEMENT

	public synchronized void addObserver(Observer obs) {
		observers.add(obs);
	}
	public synchronized void deleteObserver(Observer obs) {
		observers.remove(obs);
	}
	public synchronized void deleteObservers() {
		observers.clear();
	}
	public synchronized int countObservers() {
		return observers.size();
	}

	// Deal with observers

	public void notifyObservers(Object data) {
		for (Observer o : observers)
			o.update(this, data);
	}

	public void notifyObservers() {
		notifyObservers(null);
	}
}
