package javafx;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// BEGIN main
public class JfxVideo extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("JavaFX Video");
		final List<String> args = getParameters().getRaw();
		
		String url = args.size() > 0 ?
			args.get(args.size() - 1) :
				"http://www.mediacollege.com/" +
				"video-gallery/testclips/20051210-w50s.flv";
		Media media = new Media(url);
				
		MediaPlayer player = new MediaPlayer(media);
		player.play();

		MediaView view = new MediaView(player);
		Group root = new Group();
		root.getChildren().add(view);
		Scene scene = SceneBuilder.create().
				width(360).height(288).
				root(root).
				fill(Color.WHITE).
				build();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
// END main
