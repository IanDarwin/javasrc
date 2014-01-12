package graphics;

import uk.co.caprica.vlcj.component.*;

public class VlcjVideo extends JFrame {

	public static void main(String[] args) {
		new VideoPlayer(
			"http://174.132.240.162:8000/;stream.nsv");
	}

	public VlcjVideo(String url) {
		setTitle("VLCJ Video");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		JPanel player = new MyPlayerPanel();
		add(player, BorderLayout.CENTER);
		pack();
		setVisible(true);
		((MyVideoPanel)player).play(url);
	}

	class MyVideoPanel extends JPanel {
		private File vlcWhere = new File("/usr/local/lib");
		private EmbeddedMediaPlayer player;

		public MyVideoPanel() {
			NativeLibrary.addSearchPath("libvlc", vlcWhere.getAbsolutePath());
			EmbeddedMediaPlayerComponent videoCanvas = 
				new EmbeddedMediaPlayerComponent();
			setLayout(new BorderLayout());
			add(videoCanvas, BorderLayout.CENTER);
			player = videoCanvas.getMediaPlayer();
		}

		public void play(String media) {
			player.prepareMedia(media);
			player.parseMedia();
			player.play();
		}
	}
}
