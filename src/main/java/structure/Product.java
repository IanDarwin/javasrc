import com.darwinsys.util.Debug;

public class Product {
	String title;
	String artist;
	Media  media;

	public Product(String artist, String title, Media media) {
		this.title = title;
		this.artist = artist;
		switch (media) {
		case book:
			Debug.println("media", title + " is a book");
			break;
		case music_cd:
			Debug.println("media", title + " is a CD");
			break;
		case music_vinyl:
			Debug.println("media", title + " is a relic");
			break;
		case movie_vhs:
			Debug.println("media", title + " is on tape");
			break;
		case movie_dvd:
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
