package threads;




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
