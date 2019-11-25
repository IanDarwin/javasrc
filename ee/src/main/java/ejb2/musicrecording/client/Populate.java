package ejb2.musicrecording.client;

import javax.naming.Context;
import javax.naming.InitialContext;

import ejb2.musicrecording.beans.MusicCategory;
import ejb2.musicrecording.beans.MusicRemote;
import ejb2.musicrecording.beans.MusicRemoteHome;
import ejb2.musicrecording.beans.PublisherRemote;
import ejb2.musicrecording.beans.PublisherRemoteHome;

public class Populate {
	static String[] pubNames = {
		"Sony", "EMI", "RCA", "Naxos", "Warner"
	};
	static String[][] musicRecs = {
		{"The Smiths", "Greatest Hits"},
		{"The Beatles", "Beatles 1"},
		{"Beach Boys", "Surf's Up"}
	};
	public static void main(String[] args) throws Exception {
		System.out.println("Getting context");
		Context ctx = new InitialContext();
		System.out.println("Looking up Publisher Home in " + ctx);
		PublisherRemoteHome ph = (PublisherRemoteHome)ctx.lookup("Publisher");
		System.out.println("Found Home: " + ph);
		PublisherRemote pubSony = null;
		try {
		for (int i=0; i<pubNames.length; i++) {
			PublisherRemote pub;
			pub = ph.create(i, pubNames[i], "New York", "212-555-1212");
			if (i == 1) {
				pubSony = pub;
			}
			System.out.println("Created " + pub);
		}
		System.out.println("Populated Publisher Table.");
		} catch (Exception e) {
			System.out.println("Caught exception " + e);
		}

		// Now some recordings
		System.out.println("Looking up MusicRecording Home in " + ctx);
		MusicRemoteHome mh = (MusicRemoteHome)ctx.lookup("MusicRecording");
		System.out.println("Found Home: " + ph);
		for (int i=0; i<musicRecs.length; i++) {
			MusicRemote mr;
			mr = mh.create(i+1, musicRecs[i][0], // artist
					musicRecs[i][1], 	// title
					MusicCategory.ROCK.ordinal(), 19.99);
			System.out.println("Created " + mr);
		}
		System.out.println("Populated Publisher Table.");
	}
}
