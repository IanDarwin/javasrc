// A FRAGMENT TO ADD TO A JFRAME TO GET MENUS -- REMOVE THIS LINE

	// PUT THESE LINES AS FIELDS -- AND REMOVE THIS LINE
	/** File, Options, Help */
	JMenu fm, em, hm;


		// PUT THE REST OF THE FILE IN YOUR CONSTRUCTOR -- AND REMOVE THIS LINE
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);		// Frame implements JMenuContainer

		JMenuItem mi;
		// The File JMenu...
		fm = new JMenu("File");
			fm.add(mi = new JMenuItem("Open",  'O')));
			fm.add(mi = new JMenuItem("Save",  'S')));
			fm.add(mi = new JMenuItem("Close", 'W')));
			fm.addSeparator();
			fm.add(mi = new JMenuItem("Print", 'P')));
			fm.addSeparator();
			fm.add(mi = new JMenuItem("Exit",  'Q')));
			mi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					System.exit(0);
				}
			});
		mb.add(fm);

		// The Options JMenu...
		em = new JMenu("Edit");
			em.add(mi = new JMenuItem("Copy",  'C')));
			em.add(mi = new JMenuItem("Paste", 'V')));
			em.addSeparator();
			em.add(new JMenuItem("Preferences"));
		mb.add(em);

		// The Help JMenu...
		hm = new JMenu("Help");
			hm.add(mi = new JMenuItem("About"));
			mi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					JOptionPane.showMessageDialog(JrLister.this,
						"Program - a great new program\n" +
						"Copyright Ian F. Darwin, http://www.darwinsys.com/",
						"About This Program",
						JOptionPane.INFORMATION_MESSAGE);
				}
			hm.add(mi = new JMenuItem("Topics"));
		mb.add(hm);

