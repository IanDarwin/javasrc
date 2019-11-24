package database;

import java.util.List;

import domain.User;

/**
 * Standalone demo program for UserDB.
 * @author Ian F. Darwin, ian@darwinsys.com
 */
public class UserDBDemo {
	public static void main(String argv[]) throws java.io.IOException {

		System.out.println("Testing Getting One...");
		User iadmin = UserDB.getInstance().getUser("iadmin");
		System.out.println(iadmin);

		System.out.println("Testing Getting All...");
		List<User> al = UserDB.getInstance().getUserList();
		al.forEach(u -> System.out.println(u));

		System.out.println("Testing privs");
		iadmin.setPassword("guten abend");
		if (iadmin.checkPassword("foo"))
			System.out.println("setPassword failed");
		if (!iadmin.checkPassword("guten abend"))
			System.out.println("checkPassword failed");
		System.out.println("All done");
		System.exit(0);
	}
}
