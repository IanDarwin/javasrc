package gui.MVC;

import javax.swing.*;
import java.util.List;

public class TextView extends JTextArea {
	public TextView() {
		super(20, 20);
	}
	
	public void setListData(List l) {
		setText("");
		for (int i=0; i< l.size(); i++) {
			append((String)l.get(i));
			append("\n");
		}
	}
}
