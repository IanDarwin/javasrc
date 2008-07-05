package gui.MVC;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** Simple data model for demos */
public class Model {
	private List<String> list = new ArrayList<String>();

	public void add(String s) {
		list.add(s);
		fireChange();
	}

	public List<String> getData() {
		return list;
	}

	// Sun recommends using javax.swing.EventListenerList, but this is easier
	List<ChangeListener> changeListeners = new ArrayList<ChangeListener>();

	public void addChangeListener(ChangeListener cl) {
		changeListeners.add( cl);
	}

	public void removeChangeListener(ChangeListener cl) {
		changeListeners.remove(cl);
	}

	protected void fireChange() {
		ChangeEvent evt = new ChangeEvent(this);
		for (int i = 0; i < changeListeners.size(); i++) {
			ChangeListener cl = changeListeners.get(i);
			cl.stateChanged(evt);
		}
	}
}
