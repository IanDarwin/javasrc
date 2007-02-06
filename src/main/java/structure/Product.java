package structure50;

import com.darwinsys.util.Debug;

public class Product {
	String title;
	String artist;
	Media  media;

	public Product(String artist, String title, Media media) {
		this.title = title;
		this.artist = artist;
		switch (media) {
		case BOOK:
			Debug.println("media", title + " is a book");
			break;
		case MUSIC_CD:
			Debug.println("media", title + " is a CD");
			break;
		case MUSIC_VINYL:
			Debug.println("media", title + " is a relic");
			break;
		case MOVIE_VHS:
			Debug.println("media", title + " is on tape");
			break;
		case MOVIE_DVD:
			Debug.println("media", title + " is on DVD");
			break;
		default:
			Debug.println("media", "Warning: " + title +
				": Unknown media " + media);
			break;
		}
		this.media = media;
	}
}
