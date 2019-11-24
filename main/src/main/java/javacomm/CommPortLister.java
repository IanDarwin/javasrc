package javacomm;

import java.util.Enumeration;

import javax.comm.CommPortIdentifier;

/**
 * List the ports.
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class CommPortLister {

	/** Simple test program. */
	public static void main(String[] ap) {
		new CommPortLister().list();
	}

	/** Ask the Java Communications API * what ports it thinks it has. */
	protected void list() {
		// get list of ports available on this particular computer,
		// by calling static method in CommPortIdentifier.
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> pList = CommPortIdentifier.getPortIdentifiers();

		// Process the list.
		boolean found = false;
		while (pList.hasMoreElements()) {
			CommPortIdentifier cpi = pList.nextElement();
			System.out.print("Port " + cpi.getName() + " ");
			if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				System.out.println("is a Serial Port: " + cpi);
				found = true;
			} else if (cpi.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
				System.out.println("is a Parallel Port: " + cpi);
				found = true;
			} else {
				System.out.println("is an Unknown Port: " + cpi);
				// Should we set this? found = true;
			}
		}
		if (!found) {
			System.out.println("No ports found");
		}
	}
}
