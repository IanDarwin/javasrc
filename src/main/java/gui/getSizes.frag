	public Dimension getMinimumSize() {
		return new Dimension(width, height);
	}
	public Dimension getPreferredSize() {
		return new Dimension(width+PAD, height+PAD);
	}
	public Dimension getMaximumSize() {
		return new Dimension(width*2, height*2);
	}
