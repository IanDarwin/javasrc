		// Check for JDK >= 1.1
		try {
			Class.forName("java.lang.reflect.Constructor");
		} catch (Exception e) {
			String failure = 
				"Sorry, but this version of JabaDex needs \n" +
				"a Java Runtime based on Java JDK 1.1 or later";
			System.err.println(failure);
			throw new IllegalArgumentException(failure);
		}
