        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// If we do setVisible and dispose, then the Close completes
				Outer.this.setVisible(false);
				Outer.this.dispose();
			}
		});
