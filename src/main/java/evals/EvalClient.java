package evals;

// INCOMPLETE 

import java.rmi.Naming;

public class EvalClient implements NetEvalInfo {

	NetEvaluation netConn = null;

	public EvalClient() {
		try {
			netConn = (NetEvaluation)Naming.lookup("//"+SERVER+"/EvalServer");
		} catch (Exception e) {
			System.err.println("NetEvaluation exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void run() {
		Evaluation e = new Evaluation("localhost", Evaluation.LHS, 
			"A student", false,
			4, 3, "Nothing", "Nothing", "Nothing");
		try {
			if (netConn != null)
				netConn.sendEval(e);
		} catch (Exception exc) {
			System.err.println("RemoteDate exception: " + exc.getMessage());
			exc.printStackTrace();
		}
	}
}
