package evals;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JFrame;

import graphics.Grapher;

public class EvalServer 
	extends UnicastRemoteObject 
	implements NetEvaluation,NetEvalInfo {

	/** The list of all Evaluations received */
	private Hashtable h;
	/** true if driving a GUI */
	private boolean isVisual = true;
	/** true to provide test data */
	private boolean initTest = true;
	/** The window frame */
	private JFrame f;
	/** The graphing code */
	private Grapher g;
	/** A decimal format for printing averages */
	private DecimalFormat df = new DecimalFormat("#.##");

	public EvalServer() throws java.rmi.RemoteException {
		super();
		// System.out.println("In EvalServer constructor");
		h = new Hashtable();
		if (!isVisual)
			return;
		f = new JFrame("Daily Evaluation Server");
		f.setLocation(100, 100);
		f.setContentPane(g = new Grapher());
		try {
			f.pack();
			f.setVisible(true);
		} catch (Error e) {
			isVisual = false;
		}
	}

	public void sendEval(Evaluation e) 
		throws java.rmi.RemoteException, DupEvalException {
		int crsTot=0, insTot=0, nEvals=0;
		// System.out.println("We got " + e);
		String ident = e.hostName + e.studentName + e.side;
		if (!e.overriding && h.get(ident) != null)
			throw new DupEvalException(ident);

		// Lock the Hashtable while reading/writing it.
		synchronized(h) {
			h.put(ident, e);

			// Must always recompute these values in case there
			// was an "overriding" evaluation
			crsTot = insTot = nEvals = 0;

			Enumeration evals;
			Evaluation tmp = null;
			for (evals = h.keys(); evals.hasMoreElements();) {
				tmp = (Evaluation)h.get(evals.nextElement());
				// System.out.println("tmp got " + tmp);
				crsTot += tmp.course;
				insTot += tmp.instr;
				nEvals++;
			}
		}

		float crsAvg = (float)crsTot/nEvals;
		float insAvg = (float)insTot/nEvals;
		System.out.println("n = " + nEvals + " " +
			"crsAvg = " + df.format(crsAvg) + " " +
			"insAvg = " + df.format(insAvg));

		if (isVisual) {
			System.out.println("XXX: Update me for current Grapher.");
			// g.setValues(nEvals, crsAvg, insAvg);
		}

		// if (e.c1 != null && c1.length != 0)
			// log(ident, e.c1);
	}

	public static void main(String[] av) throws java.rmi.RemoteException {
		EvalServer eServ = null;
		// Must create and install a security manager BEFORE
		// trying to create the object itself.
		System.setSecurityManager(new RMISecurityManager());
		try {
			eServ = new EvalServer();
			Naming.rebind("//"+SERVER+"/EvalServer", eServ);
			System.out.println("EvalServer bound in registry");
		} catch (Exception e) {
			System.out.println("EvalServer err: " + e.getMessage());
			e.printStackTrace();
		}
		if (eServ.initTest) {
			Evaluation e;
			e = new Evaluation("localhost", Evaluation.LHS, 
				"A student", false,
				4, 3, "Nothing", "Nothing", "Nothing");
			eServ.sendEval(e);
			try { Thread.sleep(5000); } catch (InterruptedException exc) {}
			e = new Evaluation("localhost", Evaluation.LHS, 
				"A stoodent", false,
				3, 2, "Nothing", "Nothing", "Nothing");
			eServ.sendEval(e);
			try { Thread.sleep(5000); } catch (InterruptedException exc) {}
			e = new Evaluation("localhost", Evaluation.LHS, 
				"A stupent", false,
				4, 4, "Nothing", "Nothing", "Nothing");
			eServ.sendEval(e);
		}
	}
}
