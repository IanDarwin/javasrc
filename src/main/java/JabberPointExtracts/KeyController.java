import java.awt.event.*;

/** This is EXTRACTS FROM the KeyController (KeyListener) for the View */
public class KeyController extends KeyAdapter {
	/** The Model which we are controlling */
	Model model;

	public KeyController(Model m) {
		model = m;		// constructor just saves it, for calling setters
	}

	public void keyPressed(KeyEvent k) {
		// System.out.println("keyPressed: " + k.getKeyCode());
		switch(k.getKeyCode()) {
		case KeyEvent.VK_PAGE_DOWN:
		case KeyEvent.VK_ENTER:
			model.nextPage();
			break;
		case KeyEvent.VK_PAGE_UP:
			model.prevPage();
			break;
		default:
			break;
		}
	}
}
