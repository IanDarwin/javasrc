package sched;

/** Generic(?) Event Handling for the DateSelectedListeners */
import java.util.List;
import java.util.Vector;
public class CalEventMgr {
	public List<DateSelectedListener> v = new Vector<DateSelectedListener>();

	public void addDateSelectedListener(DateSelectedListener dl) {
		v.add(dl);
	}

	public void removeDateSelectedListener(DateSelectedListener dl) {
		v.remove(dl);
	}

	public void fireEvent(DateSelectedEvent dse) {
		Object s = dse.getSource();
		// System.out.println(s + " says fireEvent( " + dse + ")");
		for (int i=0; i<v.size(); i++) {
			DateSelectedListener l = ((DateSelectedListener)v.get(i));
			if (l == s)
				continue;
			System.out.println("Telling " + l + " about " + dse);
			l.dateSelected(dse);
		}
	}
}
