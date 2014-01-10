package javafx;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlay {
	public static void main(String[] args) {
		String clipName = "demo.mp3";
		Media clip = new Media(clipName);
		MediaPlayer mediaPlayer = new MediaPlayer(clip);
		mediaPlayer.play();
	}
}
