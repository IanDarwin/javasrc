		try {
			Class.forName("javax.swing.JButton");
		} catch (Exception e) {
			String failure = 
				"Sorry, but this version of JabaDex needs \n" +
				"a Java Runtime that includes JFC/Swing components\n" +
				"with the new (1.2) names (javax.swing.*)";
			System.err.println(failure);
			cp.add(new Label(failure));
			throw new IllegalArgumentException(failure);
		}
