package graphics;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/** Simple program to try out the "new Sound" stuff in JDK1.2 --
 * allows Applications, not just Applets, to play Sound.
 */
// BEGIN main
public class SoundPlay {

	static String defSounds[] = {
		"/audio/test.wav",
		"/music/midi/Beet5th.mid",
	};

	public static void main(String[] av) {
		if (av.length == 0)
			main(defSounds);
		else for (String a : av) {
			System.out.println("Starting " + a);
			try {
				URL snd = SoundPlay.class.getResource(a);
				if (snd == null) {
					System.err.println("Cannot getResource "  + a);
					continue;
				}
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(snd);
				final Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (Exception e) {
				System.err.println(e);
			}
	 	}
	}
}
// END main
