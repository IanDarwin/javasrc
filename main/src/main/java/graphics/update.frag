	// This is the default "update" from JDK1.0.
	// As you can see, it just clears the Component and sets
	// the drawing color to the Foreground color, and calls your
	// paint() method.
	public void update(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, width, height);	
		g.setColor(getForeground());
		paint(g);
	}
