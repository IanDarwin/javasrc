import java.rmi.*;

/** The interface for sending stuff over the net */
public interface NetEvaluation {
	public void sendEval(Evaluation e) 
		throws java.rmi.RemoteException, DupEvalException;
}
	
