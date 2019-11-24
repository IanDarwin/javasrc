package structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * A trivial re-implementation of the java.util.Observable class,
 * with additionally the ability to ignore the need for setChanged() calls.
 * Only extends Observable to allow use of existing Observer.update(Observable, data)
 */
public class MyObservable extends Observable implements Serializable {
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

	@Override
	public synchronized void addObserver(Observer obs) {
		observers.add(obs);
	}
	@Override
	public synchronized void deleteObserver(Observer obs) {
		observers.remove(obs);
	}
	@Override
	public synchronized void deleteObservers() {
		observers.clear();
	}
	@Override
	public synchronized int countObservers() {
		return observers.size();
	}

	// Deal with observers

	@Override
	public void notifyObservers(Object data) {
		if (strictMode && !changed)
			return;
		for (Observer o : observers)
			o.update(this, data);
		changed = false;
	}

	@Override
	public void notifyObservers() {
		notifyObservers(null);
	}

	// Deal with the Changed flag

	@Override
	protected synchronized void setChanged() {
		this.changed = true;
	}

	@Override
	protected synchronized void clearChanged() {
		this.changed = false;
	}

	// NOT AN OVERRIDE - added for testing
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	@Override
	public synchronized boolean hasChanged() {
		return changed;
	}
}
