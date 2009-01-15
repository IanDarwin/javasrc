package ejb2.musicrecording.beans;

/** MusicCategory is the list of categories.
 * NEVER ADD IN THE MIDDLE - only add at the end - since
 * the ordinal() value is used as values in the database!!
 */
public enum MusicCategory {
	NONE,
	CLASSICAL,
	JAZZ,
	ROCK
}
