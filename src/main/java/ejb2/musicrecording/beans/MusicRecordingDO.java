package com.darwinsys.rain;

/** Dependant-value object for Music Recording.
 * Intentionally Immutable. Send this, instead of setting
 * or getting individual fields.
 * @author Ian Darwin
 */
public class MusicRecordingDO implements java.io.Serializable {

	public static final int NO_PKEY = -1;

	/** Primary key: numeric id */
	protected int id;
	protected String artist;
	protected String title;
	protected int category;
	protected int publisher;
	protected double price;

	public MusicRecordingDO(
		int id,
		String artist,
		String title,
		int category,
		int publisher,
		double price) {

		this.id = id;
		this.artist    = artist;
		this.title     = title;
		this.category  = category;
		this.publisher = publisher;
		this.price     = price;
	}

	public MusicRecordingDO(
		String artist,
		String title,
		int category,
		int publisher,
		double price) {

		this(NO_PKEY, artist, title, category, publisher, price);
	}
	/** Get int */
	public int getId() {
		return id;
	}

	/** Get artist */
	public String getArtist() {
		return artist;
	}

	/** Get title */
	public String getTitle() {
		return title;
	}

	/** Get category */
	public int getCategory() {
		return category;
	}

	/** Get publisher */
	public int getPublisher() {
		return publisher;
	}

	/** Get price */
	public double getPrice() {
		return price;
	}
}
