import com.darwinsys.util.Debug;

public class Product {
	String title;
	String author;
	Media  media;

	public Product(String artist, String title, Media media) {
		this.title = title;
		this.artist = artist;
		switch (media) {
		case Media.book:
			Debug.print("media", title + " is a book");
			break;
		case Media.music_cd:
			Debug.print("media", title + " is a CD");
			break;
		case Media.music_vinyl:
			Debug.print("media", title + " is a relic");
			break;
		case Media.movie_vhs:
			Debug.print("media", title + " is on tape");
			break;
		case Media.movie_dvd:
			Debug.print("media", title + " is on DVD");
			break;
		default:
			Debug.print("media", "Warning: " + title + 
				": Unknown media " + media);
			break;
		}
		this.media = media;
	}
}
