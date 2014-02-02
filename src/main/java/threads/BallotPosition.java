package threads;

/**
 * Represents one thing that can be voted on, e.g., a candidate or a position
 */
class BallotPosition {
	String question;
	int votes;
	BallotPosition(String q) {
		question = q;
	}
	public String getName() {
		return question;
	}
	public int getVotes() {
		return votes;
	}
}
