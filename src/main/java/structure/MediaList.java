package structure;

/** Simple demo to print all the types of media. */
public class MediaList {
	public static void main(String[] args) {
		for (Media m : Media.values()) {
			System.out.println(m);
		}
	}
}
