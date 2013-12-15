package structure;

// BEGIN main
/** An example of an enum with method overriding */
public enum MediaFancy {
	/** The enum constant for a book, with a method override */
	BOOK {
		public String toString() { return "Book"; }
	},
	/** The enum constant for a Music CD */
	MUSIC_CD,
	/** ... */
	MUSIC_VINYL,
	MOVIE_VHS,
	MOVIE_DVD;

	/** It is generally disparaged to have a main() in an enum;
	 * please forgive this tiny demo class for doing so.
	 */
	public static void main(String[] args) {
		MediaFancy[] data =  { BOOK, MOVIE_DVD, MUSIC_VINYL };
		for (MediaFancy mf : data) {
			System.out.println(mf);
		}
	}
}
// END main
