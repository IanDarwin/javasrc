package reflection.showlet;

public class MyBigApp2 {

	public static void main(String[] args) throws Exception {
		String className;
		// stub getting name of user-defined class from config file
		// className = "com.darwinsys.jellybeans.IanShow";
		className = "reflection.showlet.IanShow";

		// Create this user's ShowLet
		Class c = Class.forName(className);
		Object o = c.newInstance();
		if (c.isInstance(o)) {
			Showlet s = (Showlet) c.newInstance();

			// Now we have a showlet, make it do its thing:
			s.show();
		} else {
			System.err.printf("Sorry, class %s is not a Showlet%n", className);
		}

	}
}
