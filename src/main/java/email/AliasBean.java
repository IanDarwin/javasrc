package email;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class AliasBean extends JPanel {
	private static final long serialVersionUID = 1L;

	protected List<Alias> aliasesList = new Vector<Alias>();
	protected JList<Alias> aliJList;
	private JTextField nameTF, addrTF;

	public AliasBean() {
		aliJList = new JList<>();
		aliJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		aliJList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				int i = aliJList.getSelectedIndex();
				if (i<0) return;
				Alias al = aliasesList.get(i);
				nameTF.setText(al.getName());
				addrTF.setText(al.getAddress());
			}
		});

		setLayout(new BorderLayout());
		add(BorderLayout.WEST, new JScrollPane(aliJList));

		JPanel rightPanel = new JPanel();
		add(BorderLayout.EAST, rightPanel);
		rightPanel.setLayout(new GridLayout(0, 1));

		JPanel buttons = new JPanel();
		rightPanel.add(buttons);
		buttons.setLayout(new GridLayout(0, 1, 15, 15));
		JButton b;
		buttons.add(b = new JButton("Set"));
		b.setToolTipText("Add or Change an alias");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int i = aliJList.getSelectedIndex();
				if (i<0) {
					// XXX error dialog??
					return;
				}
				setAlias(i, nameTF.getText(), addrTF.getText());
			}
		});
		buttons.add(b = new JButton("Delete"));
		b.setToolTipText("Delete the selected alias");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int i = aliJList.getSelectedIndex();
				if (i<0) {
					return;
				}
				deleteAlias(i);
			}
		});
		buttons.add(b = new JButton("Apply"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.err.println("NOT WRITTEN YET");
			}
		});

		JPanel fields = new JPanel();
		rightPanel.add(fields);
		fields.setLayout(new GridLayout(2,2));
		fields.add(new JLabel("Name"));
		fields.add(nameTF = new JTextField(10));
		fields.add(new JLabel("Address"));
		fields.add(addrTF = new JTextField(20));
	}

	public String expandAlias(String ali) {
		Alias a = findAlias(ali);
		if (a == null)
			return null;
		return a.getAddress();
	}

	public Alias findAlias(String ali) {
		for (Alias a : aliasesList) {
			if (a.getName().equals(ali))
				return a;
		}
		return null;	// not found
	}

	/** Add an Alias */
	public void addAlias(Alias a) {
		Alias al = findAlias(a.getName());
		if (al == null) {
			aliasesList.add(a);
		} else {
			// aliVector.set(a);		// XXX fuzzy
		}
		aliJList.setListData(getListDataAsArray());
	}

	/** Add an alias, by its constituent parts */
	public void addAlias(String nn, String addr) {
		addAlias(new Alias(nn, addr));
	}

	/** Replace an Alias */
	public void setAlias(int n, String nam, String addr) {
		// TODO find it, replace it, or add it.
		aliasesList.set(n, new Alias(nam, addr));
		aliJList.setListData(getListDataAsArray());
	}

	public void deleteAlias(int i) {
		aliasesList.remove(i);
		aliJList.setListData(getListDataAsArray());
	}

	private Alias[] getListDataAsArray() {
		return aliasesList.toArray(new Alias[0]);
	}
}
