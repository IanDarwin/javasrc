	// This is how components in 1.1 can use the new
	// SystemColors object to fit in to the existing
	// desktop colors. This would be in your paint() method.

	Dimension d = getSize();

	// draw highlight left and top
	g.setColor(SystemColor.controlLtHighlight);
	g.drawLine(0, 0, 0, d.height);
	g.drawLine(0, 0, d.width, 0);

	// draw background
	g.setColor(SystemColor.control);
	g.fillRect(1,1, d.width-2, d.height-2);

	// draw shadow bottom and right
	g.setColor(SystemColor.controlDkShadow);
	g.drawLine(0, d.height-1, d.width-1, d.height-1);
	g.drawLine(d.width-1, 0, d.width-1, d.height-1);