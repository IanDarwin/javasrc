package gui.datatransfer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Demonstrate various aspects of Swing "data transfer".
 * @author Ian Darwin, http://www.darwinsys.com
 * @author Jonathan Fuerth, http://www.SQLPower.ca
 */
public class Transfer extends JFrame {
	public static void main(String[] args) {
		new Transfer().setVisible(true);
	}

	private JTextField tf;
	private JLabel l;
	private JComboBox propertyComboBox;

	public Transfer() {

		// Establish the GUI
		Container cp = new Box(BoxLayout.X_AXIS);
		setContentPane(cp);
		JPanel firstPanel = new JPanel();
		propertyComboBox = new JComboBox();
		propertyComboBox.addItem("text");
		propertyComboBox.addItem("font");
		propertyComboBox.addItem("background");
		propertyComboBox.addItem("foreground");
		firstPanel.add(propertyComboBox);
		cp.add(firstPanel);
		cp.add(Box.createGlue());

		tf = new JTextField("Hello");
		tf.setForeground(Color.RED);
		tf.setDragEnabled(true);
		cp.add(tf);

		cp.add(Box.createGlue());

		l = new JLabel("Hello");
		l.setBackground(Color.YELLOW);
		cp.add(l);

		cp.add(Box.createGlue());

		JSlider stryder = new JSlider(SwingConstants.VERTICAL);
		stryder.setMinimum(10);
		stryder.setValue(14);
		stryder.setMaximum(72);
		stryder.setMajorTickSpacing(10);
		stryder.setPaintTicks(true);

		cp.add(stryder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);

		// Add Listeners and Converters
		setMyTransferHandlers((String)propertyComboBox.getSelectedItem());

		// Mousing in the Label starts a Drag.
		MouseListener myDragListener = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JComponent c = (JComponent)e.getSource();
				TransferHandler handler = c.getTransferHandler();
				handler.exportAsDrag(c, e, TransferHandler.COPY);
			}
		};
		l.addMouseListener(myDragListener);

		// Selecting in the ComboBox makes that the property that is xfered.
		propertyComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ce) {
				JComboBox bx = (JComboBox)ce.getSource();
				String prop = (String)bx.getSelectedItem();
				setMyTransferHandlers(prop);
			}
		});

		// Typing a word and pressing enter in the TextField tries
		// to set that as the font name.
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JTextField jtf = (JTextField)evt.getSource();
				String fontName = jtf.getText();
				Font font = new Font(fontName, Font.BOLD, 18);
				tf.setFont(font);
			}
		});

		// Setting the Slider sets that font into the textfield.
		stryder.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt)  {
				JSlider sl = (JSlider)evt.getSource();
				Font oldf = tf.getFont();
				Font newf = oldf.deriveFont((float)sl.getValue());
				tf.setFont(newf);
			}
		});

	}

	private void setMyTransferHandlers(String s) {
		TransferHandler th = new TransferHandler(s);
		tf.setTransferHandler(th);
		l.setTransferHandler(th);
	}
}
