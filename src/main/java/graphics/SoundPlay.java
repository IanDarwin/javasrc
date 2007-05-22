package graphics;

import java.applet.Applet;
import java.net.URL;

/** Simple program to try out the "new Sound" stuff in JDK1.2 --
 * allows Applications, not just Applets, to play Sound.
 */
public class SoundPlay {

	static String defSounds[] = {
		"file:///javasrc/graphics/test.wav",
		"file:///music/midi/Beet5th.mid",
	};

	public static void main(String[] av) {
		if (av.length == 0)
			main(defSounds);
		else for (int i=0;i<av.length; i++) {
			System.out.println("Starting " + av[i]);
			try {
				URL snd = new URL(av[i]);
				// open to see if works or throws exception, close to free fd's
				// snd.openConnection().getInputStream().close();
				Applet.newAudioClip(snd).play();
			} catch (Exception e) {
				System.err.println(e);
			}
	 	}
		// With this call, program exits before/during play.
		// Without it, on some versions, program hangs forever after play.
		// System.exit(0);
	}
}
