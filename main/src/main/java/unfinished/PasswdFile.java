package unfinished;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PasswdFile extends JFrame {
	public PasswdFile() {
		setTitle("Password File");

		Container cp = new Box(BoxLayout.X_AXIS);
		setContentPane(cp); 

		Box bl, bc, br;

		bl = new Box(BoxLayout.Y_AXIS);
		bl.add(new JLabel("Card"));
		bl.add(new JLabel("Lock"));
		bl.add(new JLabel("Note"));
		cp.add(bl);

		// Middle panel (bc) gets Description, Details, and Comments.
		bc = new Box(BoxLayout.Y_AXIS);

		JPanel descPanel = new JPanel();
		descPanel.setBorder(BorderFactory.createEtchedBorder());
		descPanel.add(new JLabel("Description"));
		JComboBox profiles = new JComboBox();
		profiles.setEditable(true);
		descPanel.add(profiles);
		bc.add(descPanel);

		JPanel passPanel = new JPanel();
		passPanel.setLayout(new GridLayout(0, 2));
		passPanel.setBorder(BorderFactory.createEtchedBorder());
		passPanel.add(new JLabel("User Name:"));
		passPanel.add(new JTextField(10));
		passPanel.add(new JLabel("Password:"));
		passPanel.add(new JTextField(30));
		passPanel.add(new JLabel("Hint:"));
		passPanel.add(new JTextField(30));
		passPanel.add(new JLabel("URN:"));
		passPanel.add(new JTextField(30));
		bc.add(passPanel);

		JTextArea comments = new JTextArea(3,30);
		bc.add(comments);

		cp.add(bc);

		// Right hand column is just action buttons
		br = new Box(BoxLayout.Y_AXIS);
		JButton b;
		br.add(b = new JButton("New"));
		br.add(b = new JButton("Save"));
		br.add(b = new JButton("Cancel"));
		br.add(b = new JButton("Delete"));
		br.add(new Spacer());
		br.add(b = new JButton("Help"));
		br.add(b = new JButton("About"));
		br.add(new Spacer());
		br.add(b = new JButton("Exit"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cp.add(br);

		pack();
	}

	public static void main(String[] args) {
		new PasswdFile().setVisible(true);
	}

	class Spacer extends Component {
		public Dimension getPreferredSize() {
			return new Dimension(10, 10);
		}
	}
}
