		try {
			Class.forName("javax.swing.JButton");
		} catch (Exception e) {
			String failure = 
				"Sorry, but this version of MyApp needs \n" +
				"a Java Runtime with JFC/Swing components\n" +
				"having the final names (javax.swing.*)";
			System.err.println(failure);
			cp.add(new Label(failure));
			throw new IllegalArgumentException(failure);
		}
