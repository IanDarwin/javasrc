package graphics;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import com.sun.jna.NativeLibrary;

// BEGIN main
public class VlcjVideo extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new VlcjVideo(args[0]);
	}

	public VlcjVideo(String url) {
		setTitle("VLCJ Video");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		JPanel player = new MyVideoPanel();
		add(player, BorderLayout.CENTER);
		pack();
		setVisible(true);
		((MyVideoPanel)player).play(url);
	}

	class MyVideoPanel extends JPanel {
		private static final long serialVersionUID = 1L;
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
// END main
