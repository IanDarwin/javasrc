package threads;

import java.util.Iterator;
import java.util.List;

import com.darwinsys.util.ArrayIterator;

/**
 * BallotBox - keep track of voting. Only used in ReadersWritersDemo.
 */
class BallotBox implements Iterable {
	final BallotPosition[] data;
	
	@SuppressWarnings("unchecked")
	public Iterator<BallotPosition> iterator() {
		return new ArrayIterator(data);
	}
	
	/** Construct a BallotBox given a List<String> 
	 * containing e.g., the names of the candidates
	 * or positions being voted upon. We copy the
	 * references to avoid having the list changed in
	 * another thread or even this thread between calls here.
	 */
	BallotBox(List<String> list) {
		data = new BallotPosition[list.size()];
		for (int i = 0; i < list.size(); i++) {
			data[i] = new BallotPosition(list.get(i));
		}
	}
	
	public void voteFor(int i) {
		++data[i].votes;
	}
	
	int getCandidateCount() {
		return data.length;
	}
}
