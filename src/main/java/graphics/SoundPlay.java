import java.applet.*;
import java.net.*;

/** Simple program to try out the "new Sound" stuff in JDK1.2 --
 * allows Applications, not just Applets, to play Sound.
 */
public class P {
	static String defSounds[] = {
		"file:///windows/media/chord.wav",
		"file:///windows/media/Beethovens Fur Elise.rmi",
	};
	public static void main(String av[]) {
		if (av.length == 0)
			main(defSounds);
		else for (int i=0;i<av.length; i++) {
			System.out.println("Starting " + av[i]);
			try {
				URL snd = new URL(av[i]);
				// open to see if works or throws exception, close to free fds
				snd.openConnection().getInputStream().close();
				Applet.newAudioClip(snd).play();
			} catch (Exception e) {
				System.err.println(e);
			}
	 	}
		// With this call, program exits before/during play.
		// Without it, program hangs forever after play.
		// System.exit(0);
	}
}
