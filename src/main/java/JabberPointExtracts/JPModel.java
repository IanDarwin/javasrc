import java.util.*;
/**
 * EXTRACTS FROM JPModel, the data model for JabberPoint.
 * It keeps track of all the slides in the presentation.
 */
public class JPModel extends Observable {
	/** the Vector of Slides */
	Vector show = new Vector();
	/** The currently-displayed page */
	int pageNumber = 0;

	/** Append a slide to the presentation */
	public void append(Slide s) {
		show.addElement(s);
	}

	public Slide getPage() {
		return (Slide)show.elementAt(pageNumber);
	}

	/* Here are some methods used in the Controller(s) to control
	 * what part of the data the view displays:
	 */

	/** setPage(int pnum) sets the current page(slide) and 
	 * notifies all the view(s) of the current page
	 */
	public void setPage(int i) {
		if (i<0 || i>=show.size())
			throw new IllegalArgumentException();
		pageNumber = i;
		setChanged();				// for the Observers (required!)
		notifyObservers(getPage());	// tell the observers the current page
	}
	/** Move to the previous page, unless at beginning */
	public void prevPage() {
		if (pageNumber > 0)
			setPage(pageNumber-1);
	}
	// nextPage() is the same but in reverse
}
