package evals;

/** The data that we send over the wire */
public class Evaluation {
	String hostName;	// origin
	int side;			// which student of 2 at ws?
	public static final int LHS = 1, RHS = 2, OTHER = 3;
	String studentName;
	boolean overriding;	// true if student wants to revise

	int instr;		// 0, 1, 2, 3, or 4
	int course;		// 0, 1, 2, 3, or 4
	String	canImprove;
	String	bestPart;
	String	suggestion;

	public Evaluation(String hn, int side, String sn, boolean repl,
		int crs, int ins, String c1, String c2, String c3) {
			hostName = hn;
			this.side = side;
			studentName = sn;
			overriding = repl;
			course = crs;
			instr = ins;
			canImprove = c1;
			bestPart = c2;
			suggestion = c3;
	}

	public String toString() {
		return "Evaluation[" +
		hostName + "," +
		side  + "," +
		studentName  + "," +
		overriding  + "," +
		course  + "," +
		instr  + "]";
	}
}
