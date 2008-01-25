package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

import com.darwinsys.swingui.SearchBox;

public class SearchBoxDemo extends JFrame {

	private static final long serialVersionUID = -4084286370458323419L;

	private String words[] = {
			"quinacrine",
			"Java Cookbook",
			"extraequilibrium",
			"execrable",
			"intransigent"
	};
	
	private JList vlist = new JList(words);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final SearchBoxDemo demo = new SearchBoxDemo();
		demo.setVisible(true);
	}
	
	Vector<String> match(String search) {
		Vector<String> results = new Vector<String>();
		if (search == null || search.length() == 0) {
			return results;
		}
		for (String i : words) {
			if (i.contains(search)) {
				results.add(i);
			}
		}
		return results;
	}

	SearchBoxDemo() {
		setLayout(new BorderLayout());
		SearchBox box = new SearchBox();
		add(BorderLayout.NORTH, box);
		add(BorderLayout.CENTER, vlist);
		
		final JTextField textField = box.getTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				final String search = textField.getText();
				System.out.println("Search String: " + search);
				vlist.setListData(match(search));
			}
		});
		pack();
	}
}
