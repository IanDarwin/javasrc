package gui.MVC;

import java.util.*;
import javax.swing.event.*;

public class Model {
	private List list = new ArrayList();

	public void add(String s) {
		list.add(s);
		fireChange();
	}

	public List getData() {
		return list;
	}

	// Sun recommends using javax.swing.EventListenerList, but this is easier
	List changeListeners = new ArrayList();

	public void addChangeListener(ChangeListener cl) {
		changeListeners.add( cl);
	}

	public void removeChangeListener(ChangeListener cl) {
		changeListeners.remove(cl);
	}

	protected void fireChange() {
		ChangeEvent evt = new ChangeEvent(this);
		for (int i = 0; i < changeListeners.size(); i++) {
			ChangeListener cl = (ChangeListener)changeListeners.get(i);
			cl.stateChanged(evt);
		}
	}
}
