/*
 * MediaFactory - give out Media enumerations
 * @author ian
 * @verion $Id$
 */
public class MediaFactory {

	public static void main(String[] args) {
		
		System.out.println(MediaFactory.getMedia("Book"));
	}
	public static Media getMedia(String s) {
		return Enum.valueOf(Media.class, s.toLowerCase());
	}
	public static Media getMedia(int n){
		return Media.values()[n];
	}
}
