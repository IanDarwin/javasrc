package network;

import java.net.*;
/**
 * Probe a network address for useful information.
 */
public class Probe {
	public static void main(String[] argv) {
		if (argv.length == 0) {
			System.err.println("lusage: Probe hostname [...]");
			return;
		}
		Probe p = new Probe();
		for (int i=0; i<argv.length; i++)
			p.probe(argv[i]);
	}
	/** Do all the probing for one hostname */
	void probe(String hn) {
		System.out.println("Java NetProbe 0.0: probing " + hn);
		try {
			InetAddress all[] = InetAddress.getAllByName(hn);
			System.out.println(hn + " has " + all.length + " addresses.");
			for (int i=0; i<all.length; i++)
				System.out.println("Address " + all[i]);
		} catch (UnknownHostException ue) {
			System.out.println("Unknown host " + hn);
		}
	}
}
