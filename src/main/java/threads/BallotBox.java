import java.util.Iterator;
import java.util.List;

import com.darwinsys.util.ArrayIterator;

/**
 * BallotBox - keep track of voting. Only used in ReadersWritersDemo.
 * @version $Id$
 */
class BallotBox {
	BallotPosition[] data;
	
	public Iterator iterator() {
		return new ArrayIterator(data);
	}
	
	BallotBox(List list) {
		data = new BallotPosition[list.size()];
		for (int i = 0; i < list.size(); i++) {
			data[i] = new BallotPosition((String)list.get(i));
		}
	}
	
	public void voteFor(int i) {
		++data[i].votes;
	}
	
	int getCandidateCount() {
		return data.length;
	}
}