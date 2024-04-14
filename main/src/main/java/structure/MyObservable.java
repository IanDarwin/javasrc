package structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

interface Observer {
  public abstract void update(MyObservable obs, Object news);
}

/**
 * A trivial re-implementation of the java.util.Observable class,
 * with additionally the ability to ignore the need for setChanged() calls.
 * Only extends Observable to allow use of existing Observer.update(Observable, data)
 */
public class MyObservable {
	final boolean strictMode;
	final List<Observer> observers = new ArrayList<Observer>();
	private boolean changed;

	public MyObservable(boolean strictMode) {
		this.strictMode = strictMode;
	}

	public MyObservable() {
		this(true);
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
		if (strictMode && !changed)
			return;
		for (Observer o : observers)
			o.update(this, data);
		changed = false;
	}

	public void notifyObservers() {
		notifyObservers(null);
	}

	// Deal with the Changed flag

	protected synchronized void setChanged() {
		this.changed = true;
	}

	protected synchronized void clearChanged() {
		this.changed = false;
	}

	// NOT AN OVERRIDE - added for testing
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public synchronized boolean hasChanged() {
		return changed;
	}
}
