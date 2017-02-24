package javafx;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// BEGIN main
/** Simple Audio Playing with JavaFX */
public class AudioPlay {
	public static void main(String[] args) throws Exception {
		String clipName = "demo.mp3";
		URL url = new URL(String.format("file:///%s/%s", System.getProperty("user.dir"), clipName));
		Media clip = new Media(url.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(clip);
		mediaPlayer.play();
	}
}
// END main
