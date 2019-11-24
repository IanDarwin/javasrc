package threads;

import java.util.Iterator;
import java.util.List;

import com.darwinsys.util.ArrayIterator;

/**
 * BallotBox - keep track of voting. Only used in ReadersWritersDemo.
 */
class BallotBox implements Iterable<BallotPosition> {
	final BallotPosition[] data;
	
	@Override
	public Iterator<BallotPosition> iterator() {
		return new ArrayIterator<BallotPosition>(data);
	}
	
	/** Construct a BallotBox - a list of BallotPositions - 
	 * given a List<String> containing the names of the candidates
	 * or positions being voted upon.
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
