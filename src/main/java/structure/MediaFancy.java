package structure;

/** An example of an enum with method overriding */
public enum MediaFancy {
	/** The enum constant for a book, with a method override */
	book {
		public String toString() { return "Book"; }
	},
	/** The enum constant for a Music CD */
	music_cd,
	/** ... */
	music_vinyl,
	movie_vhs,
	movie_dvd;

	/** It is generally disparaged to have a main() in an enum;
	 * please forgive this tiny demo class for doing so.
	 */
	public static void main(String[] args) {
		MediaFancy[] data =  { book, movie_dvd, music_vinyl };
		for (MediaFancy mf : data) {
			System.out.println(mf);
		}
	}
}
