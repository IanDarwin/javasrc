package evals;

import java.rmi.RemoteException;

/** The interface for sending stuff over the net */
public interface NetEvaluation {
	public void sendEval(Evaluation e) 
		throws RemoteException, DupEvalException;
}
	
