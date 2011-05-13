package secure;

/**
 * http://xkcd.com/327/.  Nuff said!
 */
public class BobbyTables {

	public static String COMMAND = 
		"select id, name from students where name = '%s'";
	public static String GOOD_INPUT = "Robert Tables";
	public static String MALICIOUS_INPUT =
		"Robert'; DROP TABLE STUDENTS; --";

	public static void main(String[] args) {

		System.out.println("Executing: " + 
			String.format(COMMAND, GOOD_INPUT));
		System.out.println("Executing: " + 
			String.format(COMMAND, MALICIOUS_INPUT));
		
	}
}
