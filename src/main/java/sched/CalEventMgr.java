package sched;

/** Generic(?) Event Handling for the DateSelectedListeners */
import java.util.Vector;
public class CalEventMgr {
	public Vector v = new Vector();

	public void addDateSelectedListener(DateSelectedListener dl) {
		v.addElement(dl);
	}

	public void removeDateSelectedListener(DateSelectedListener dl) {
		v.removeElement(dl);
	}

	public void fireEvent(DateSelectedEvent dse) {
		Object s = dse.getSource();
		// System.out.println(s + " says fireEvent( " + dse + ")");
		for (int i=0; i<v.size(); i++) {
			DateSelectedListener l = ((DateSelectedListener)v.elementAt(i));
			if (l == s)
				continue;
			System.out.println("Telling " + l + " about " + dse);
			l.dateSelected(dse);
		}
	}
}
