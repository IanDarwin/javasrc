package ejb2.payment;

import java.rmi.RemoteException;

public interface PaymentMethods {
	public void payByCash(int custno, double amt) throws RemoteException;
	public void payByCheque(int custno, double amt) throws RemoteException;
	public void payByCredit(int custno, double amt) throws RemoteException;
}
