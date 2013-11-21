package javacomm;

import javax.comm.CommPortOwnershipListener;
import javax.swing.JOptionPane;

/** Demonstrate the port conflict resolution mechanism.
 * Run two copies of this program and choose the same port in each.
 * $Id$
 */
public class PortOwner extends CommPortOpen {
	/** A name for showing which of several instances of this program */
	String myName;

	public PortOwner(String name) throws Exception {
		super(null);
		myName = name;
		thePortID.addPortOwnershipListener(new MyResolver());
	}

	public void converse() {
		// lah de dah...
		// To simulate a long conversation on the port...
		
		try {
			Thread.sleep(1000 * 1000);
		} catch (InterruptedException cantHappen) {
			//
		}
	}

	/** An inner class that handles the ports conflict resolution. */
	class MyResolver implements CommPortOwnershipListener {
		protected boolean owned = false;
		public void ownershipChange(int whaHoppen) {
			switch (whaHoppen) {
			case PORT_OWNED:
				System.out.println("An open succeeded.");
				owned = true;
				break;
			case PORT_UNOWNED:
				System.out.println("A close succeeded.");
				owned = false;
				break;
			case PORT_OWNERSHIP_REQUESTED: 
				if (owned) {
					if (JOptionPane.showConfirmDialog(null,
						"I've been asked to give up the port, should I?",
						"Port Conflict (" + myName + ")",
						JOptionPane.OK_CANCEL_OPTION) == 0)
					thePort.close();
				} else {
					System.out.println("Somebody else has the port");
				}
			}
		}
	}

	public static void main(String[] argv) throws Exception {

		if (argv.length != 1) {
			System.err.println("Usage: PortOwner aname");
			System.exit(1);
		}
			
		new PortOwner(argv[0]).converse();

		System.exit(0);
	}
}
