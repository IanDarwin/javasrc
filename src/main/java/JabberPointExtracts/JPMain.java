/** EXTRACTS FROM JabberPoint Main Program */
public class JPMain {
	/** The Frame for the View */
	Frame frame;
	/** The model */
	static JPModel model;
	/** The view */
	static View view;

	/** The Real Main Program */
	public static void main(String av[]) {

		JPMain jp = new JPMain();

		// if (argv.length == 0) // run a demo program
		jp.doDemo();
		// else read and parse a slideshow file(s)...

		// Start view at first page
		jp.model.setPage(0);
	}

	/** Construct a JPMain Program */
	JPMain() {

		model = new JPModel();			// model,
		view = new View();
		model.addObserver(view);		// view,

		frame = new Frame("JabberPoint 0.0");	// GUI
        frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.add(view);
		frame.pack();
		frame.setVisible(true);

		frame.addKeyListener(new KeyController(model));	// and controller

		// construct other stuff like Styles here...
	}

	/** Run a demo for testing */
	public void doDemo() {
		Slide s = new Slide();
		model.append(s);
		s.append(0, "JabberPoint Slide Styles Demonstration");
		s.append(1, "Main Point");
		s.append(2, "Sub Point");
		s.append(1, "A Far Point");
		s.append(1, "A Powerful Point");
		s.append(1, "A Jabberful Point");
		s.append(2, "Sub Point");
		s.append(3, "SubSub Point");
		s.append(4, "SubSubSub Point");
		// Page 2
		s = new Slide();
		model.append(s);
		s.append(0, "Slide The Second");
		s.append(1, "Main Point of Slide 2");
	}
}
