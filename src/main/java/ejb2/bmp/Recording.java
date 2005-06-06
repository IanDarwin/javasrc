package ejb.bmp;

/** This is a dummy version of Recording used in my BMP example. */
public class Recording {
	int id;
	String title;
	String artist;	// should be int fkey to artists table
	int publisher;	// fkey to publishers table
	int year;
}
