package structure;

// BEGIN main
public class Product {
	String title;
	String artist;
	Media  media;

	public Product(String artist, String title, Media media) {
		this.title = title;
		this.artist = artist;
		this.media = media;
	}
	
	@Override
	public String toString() {
		switch (media) {
		case BOOK:
			return title + " is a book";
		case MUSIC_CD:
			return title + " is a CD";
		case MUSIC_VINYL:
			return title + " is a relic of the age of vinyl";
		case MOVIE_VHS:
			return title + " is on old video tape";
		case MOVIE_DVD:
			return title + " is on DVD";
		default:
			return title + ": Unknown media " + media;
		}		
	}
}
// END main
