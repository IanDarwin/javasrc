import java.awt.*;
import java.awt.event.*;

public class Mover2 extends Mover {
	public void init() {
		super.init();
		Button bx = new Button("Another");
		bx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				startThread();
			}
		});
		add(bx);
	}
}
