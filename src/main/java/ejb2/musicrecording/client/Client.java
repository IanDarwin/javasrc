import com.darwinsys.rain.*;

import javax.ejb.*;
import java.rmi.*;
import javax.naming.*;

public class Client {
	public static void main(String[] args) throws Exception {
		System.out.println("Getting context");
		Context ctx = new InitialContext();
		System.out.println("Looking up Home in " + ctx);
		MusicRemoteHome mh = (MusicRemoteHome)ctx.lookup("MusicRecording");
		System.out.println("Found Home: " + mh);

		MusicRemote mr;
		try {
			mr = mh.findByPrimaryKey(new Integer(1234));
			System.out.println("Found " + mr);
		} catch (FinderException ex) {
			mr = mh.create("DarwinIan", "Greatest His/Hers", 0, 19.99);
			System.out.println("Created " + mr);

			PublisherRemoteHome ph = (PublisherRemoteHome)ctx.lookup("Publisher");
			PublisherRemote pub = ph.findByPrimaryKey(new Integer(3));
			System.out.println("Found publisher: " + pub);

			// Can't do this in a remote client; EJB2 CMR only 
			// works with Local interfaces!
			// mr.setPublisher(pub);
		}
		if (false) {
			mr.remove();
			System.out.println("Gone but not forgotten");
		}
	}
}
