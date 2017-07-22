package javafx;

import java.net.URL;
import java.util.List;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

// BEGIN main
/** Simple Audio Playing with JavaFX */
public class AudioPlay extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		List<String> args = getParameters().getRaw();
		if (args.size() == 0 ) {
			playClip(AudioPlay.class.getResource("/audio/test.wav"));
		} else {
			for (String arg : args) {
				playClip(
					// Wrap in a URL; constructor does validation for us
					arg.indexOf(':') > -1 ? new URL(arg) :
					new URL(String.format("file:///%s/%s", 
						System.getProperty("user.dir"), arg)));
			}
		}
	}

	public void playClip(URL clipUrl) {
		System.out.println("URL is " + clipUrl);
		Media clip = new Media(clipUrl.toString());
		MediaPlayer mediaPlayer = new MediaPlayer(clip);
		mediaPlayer.play();
	}
}
// END main
