package database;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import domain.User;

/** A GUI-based User Database Administrator program
 * This does NOT use the UserDB interface as it needs
 * to be able to do ANYTHING to the database,
 * to go beyond, and to repair any errors introduced
 * by bugs in the UserDB code and/or queries. :-)
 *
 * If using InstantDB, therefore, you MUST NOT RUN THIS PROGRAM
 * while users have access to the system, or the database will
 * get worse instead of better!
 *
 */
public class JDAdmin extends JFrame {

	private static final long serialVersionUID = -3463907575826646044L;
	/** the list of users */
	protected ArrayList<User> userList = new ArrayList<User>();
	/** The database connection */
	protected Connection conn;
	/** A Statement for listing users */
	protected PreparedStatement listUsersStatement;
	/** A Statement for deleting users */
	protected PreparedStatement deleteUserStatement;
	/** A Statement for resetting passwords for forgetful users */
	protected PreparedStatement setPasswordStatement;

	/** The main table */
	protected JTable theTable;

	/** Main program -- driver */
	public static void main(String av[]) throws Exception {
		JDAdmin aFrame = new JDAdmin();
		aFrame.populate();
		// aFrame.pack();
		aFrame.setSize(600,450);
		aFrame.setVisible(true);
	}

	/** Constructor */
	public JDAdmin() throws SQLException {
		super("JabaDotAdmin");

		// INIT THE DB 
		// Do this before the GUI, since JDBC does more-delayed
		// type checking than Swing...
		
		String dbDriver = JDConstants.getProperty("jabadot.jabadb.driver");
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(this,
				"JDBC Driver Failure:\n" + ex, "Error",
				JOptionPane.ERROR_MESSAGE);
		}
		conn = DriverManager.getConnection(
			JDConstants.getProperty("jabadot.jabadb.url"));
		listUsersStatement = conn.prepareStatement("select * from users");
		deleteUserStatement = 
			conn.prepareStatement("delete from users where name = ?");
		setPasswordStatement = conn.prepareStatement(
			"update users SET password = ? where name = ?");

		// INIT THE GUI
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(new JScrollPane(theTable = new JTable(new MyTableModel())),
			BorderLayout.CENTER);
		JPanel bp = new JPanel();
		JButton x;
		bp.add(x = new JButton("Delete"));
		x.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
					int r = theTable.getSelectedRow();
					if (r == -1) {
						JOptionPane.showMessageDialog(JDAdmin.this,
							"Please select a user to delete", "Error", 
							JOptionPane.ERROR_MESSAGE);
						return;
					}
					int i = JOptionPane.showConfirmDialog(JDAdmin.this,
							"Really delete user?", "Confirm", 
							JOptionPane.YES_NO_OPTION);
					switch(i) {
						case 0:
							try {
								delete(r);
							} catch (SQLException e) {
								JOptionPane.showMessageDialog(JDAdmin.this,
								"SQL Error:\n" + e, "Error", 
								JOptionPane.ERROR_MESSAGE);
							}
							break;
						case 1:
							// nothing to do.
							break;
						default:
							System.err.println("showConfirm: unex ret " + i);
					}
			}
		});
		bp.add(x = new JButton("List"));
		x.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				try {
					populate();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(JDAdmin.this,
					"SQL Error:\n" + e, "Error", 
					JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		bp.add(x = new JButton("Exit"));
		x.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				System.exit(0);
			}
		});
	
		cp.add(bp, BorderLayout.SOUTH);

	}

	/** Get the current list of users from the database
	 * into the ArrayList, so the display will be up-to-date
	 * after any major change.
	 */
	public void populate() throws SQLException {
		ResultSet rs = listUsersStatement.executeQuery();
		userList.clear();
		while (rs.next()) {
			String nick = rs.getString(1);
			// System.out.println("Adding " + nick);
			User u = new User(nick, rs.getString(UserDB.PASSWORD),
				rs.getString(UserDB.FULLNAME),
				rs.getString(UserDB.FULLNAME),
				rs.getString(UserDB.EMAIL), 
				rs.getString(UserDB.CITY),
				rs.getString(UserDB.PROVINCE),
				rs.getString(UserDB.COUNTRY),
				new Date(), new Date(),
				null, false,false);
			userList.add(u);
		}
		rs.close();
		theTable.repaint();
	}

	/** Delete the given user, by row number 
	 * (row number in the display == index into the ArrayList).
	 * Use a JDBC PreparedStatement; if it succeeds, then also
	 * remove the user object from the ArrayList.
	 */
	public void delete(int x) throws SQLException {
		User u = (User)userList.get(x);
		String nick = u.getName();
		deleteUserStatement.setString(1, nick);
		int n;
		switch (n = deleteUserStatement.executeUpdate()) {
			case 0:
				// no match!
				JOptionPane.showMessageDialog(this,
					"No match for user " + nick, "Error",
					JOptionPane.ERROR_MESSAGE);
				break;
			case 1:
				// OK
				JOptionPane.showMessageDialog(this,
					"User " + nick + " deleted.", "Done",
					JOptionPane.INFORMATION_MESSAGE);
				userList.remove(x);
				break;
			default:
				// Ulp! Deleted too many! -- n
				JOptionPane.showMessageDialog(this,
					"Oops, we deleted " + n + " users!!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		theTable.repaint();
	}

	// class extends TableModel...
	class MyTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 7754749245721237533L;

		/** Returns the number of items in the list. */
		public int getRowCount()  {
			return userList.size();
		}

		/** Return the width of the table */
		public int getColumnCount() {
			return 8;
		}

		/** Get the name of a given column */
		public String getColumnName(int i) {
			switch(i) {
			case UserDB.NAME-1:		return "Nickname";
			case UserDB.PASSWORD-1:	return "Password";
			case UserDB.FULLNAME-1:	return "Full Name";
			case UserDB.EMAIL-1:	return "Email";
			case UserDB.CITY-1:		return "City";
			case UserDB.PROVINCE-1:	return "Province";
			case UserDB.COUNTRY-1:	return "Country";
			case UserDB.PRIVS-1:	return "Privs";
			default: return "??";
			}
		}

		/** Returns a data value for the cell at columnIndex and rowIndex.
		 * MUST BE IN SAME ORDER as setValueAt();
		 */
		public Object getValueAt(int row, int col)  {
			User u = (User) userList.get(row);
			switch (col) {
			case UserDB.NAME-1: 	return u.getName();
			case UserDB.PASSWORD-1: return u.getPassword();
			case UserDB.FULLNAME-1: return u.getFullName();
			case UserDB.EMAIL-1: 	return u.getEmail();
			case UserDB.CITY-1: 	return u.getCity();
			case UserDB.PROVINCE-1:	return u.getProvince();
			case UserDB.COUNTRY-1:	return u.getCountry();
			case UserDB.PRIVS-1:	return Integer.valueOf(u.getPrivs());
			default: return null;
			}
		}

		/** Set a value in a cell. MUSE BE IN SAME ORDER AS getValueAt. */
		public void setValueAt(Object val, int row, int col)  {
			User u = (User) userList.get(row);
			switch (col) {
			// DB Schemas start at one, Java columns at zero.
			case UserDB.PASSWORD-1:
				String newPass = (String)val;		// Get new value
				try {
					setPasswordStatement.setString(1, newPass);		// ready,
					setPasswordStatement.setString(2, u.getName());	// steady,
					setPasswordStatement.executeUpdate();		// and update!
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null,
						"SQL Error:\n" + ex.toString(), "SQL Error",
						JOptionPane.ERROR_MESSAGE);
					break;
				}
				u.setPassword(newPass);	// bypassed if DB update failed
				break;

			// Only password cells are editable.
			default:
				JOptionPane.showMessageDialog(null,
					"setValueAt" + val.getClass() + "," + val, "Logic error",
					JOptionPane.ERROR_MESSAGE);
				break;
			}
		}

		/** Only password cells are editable. */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return columnIndex == UserDB.PASSWORD-1;
		}
	}
}
