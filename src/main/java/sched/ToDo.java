package sched;

/** ToDo - represent one TODO item appointment.
 */
public class ToDo {

	/** The text of the TODO or appointment. */
	String text;

	ToDo(String t) {
		text = t;
	}

	public String getText() {
		return text;
	}

	public String toString() {
		return text;
	}
}
