package sched;

public class DateSelectedEvent extends java.util.EventObject {
	protected int yy, mm, dd;
	public DateSelectedEvent(Object source, int y, int m, int d) {
		super(source);
		yy = y;
		mm = m;
		dd = d;
	}
	public String toString() {
		return "DateSelectedEvent<"+source+">["+yy+","+mm+","+dd+"]";
	}
}
