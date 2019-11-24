package lang;

/** Bring on the conflict! */

import java.util.*;	// includes Date
import java.sql.*;	// includes Date

public class ImportConflict {
	public static void main(String[] av) {
		System.out.println(new Date());	// EXPECT COMPILE ERROR
	}
}
