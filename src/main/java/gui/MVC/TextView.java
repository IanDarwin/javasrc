package gui.MVC;

import javax.swing.*;
import java.util.List;

public class TextView extends JTextArea {

	private static final long serialVersionUID = 1L;

	public TextView() {
		super(20, 20);
	}
	
	public void setListData(List<String> l) {
		setText("");
		for (int i=0; i< l.size(); i++) {
			append(l.get(i));
			append("\n");
		}
	}
}
