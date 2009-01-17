package ejb2.musicrecording.beans;

/** MusicCategory is the list of categories.
 * NEVER ADD IN THE MIDDLE - only add at the end - since
 * the ordinal() value is used as values in the database!!
 */
public enum MusicCategory {
	/** the Category for "none", "unset" or "unknown" */
	NONE,
	/** The Category for classical music */
	CLASSICAL,
	/** The Category for Jazz music */
	JAZZ,
	/** The Category for Rock and Roll */
	ROCK
}
