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
			"crabcatcher",
			"execrable",
			"extraequilibrium",
			"flagelliferous",
			"intransigent",
			"Java Cookbook",
			"Lepidoptera",
			"quinacrine",
	};
	
	private JList vlist = new JList(words);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final SearchBoxDemo demo = new SearchBoxDemo();
		demo.setVisible(true);
	}
	
	/** Match a search string against the list, returning
	 * the words that match.
	 * @param search The string to look for.
	 * @return A Vector (since that's what JList still needs) of matches
	 */
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
