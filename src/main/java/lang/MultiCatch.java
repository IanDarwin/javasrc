package lang;

/**
 *  Try-MultiCatch demo for JavaSE 7.
 * "One Catch to Rule Them All..."
 * 
 * P L E A S E   R E A D   B E F O R E   C O M P L A I N I N G
 * This class absolutely requires Java SE 7+, so just add an exclusion rule
 * (Build Path -> Exclude) if you are living with a legacy version of Java SE.
 * 
 * @author Ian Darwin and Eclipse
 */
public class MultiCatch {
	public static void main(String[] args) {
		String clazzName = "java.lang.Object";
		try {
			Class.forName(clazzName).newInstance();
		} catch ( InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
