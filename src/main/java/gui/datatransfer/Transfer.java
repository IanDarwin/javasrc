import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import javax.swing.*;

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
	private JComboBox propertyNameCombo;

	public Transfer() {

		// Establish the GUI
		Container cp = new Box(BoxLayout.X_AXIS);
		setContentPane(cp);
		JPanel firstPanel = new JPanel();
		propertyNameCombo = new JComboBox();
		propertyNameCombo.addItem("font");
		propertyNameCombo.addItem("text");
		propertyNameCombo.addItem("background");
		propertyNameCombo.addItem("foreground");
		firstPanel.add(propertyNameCombo);
		cp.add(firstPanel);
		tf = new JTextField(10);
		tf.setDragEnabled(true);
		cp.add(tf);
		cp.add(Box.createGlue());
		l = new JLabel("Hello");
		cp.add(l);
		cp.add(Box.createGlue());
		JSlider stryder = new JSlider(SwingConstants.VERTICAL);
		stryder.setMinimum(10);
		stryder.setMaximum(72);
		cp.add(stryder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);

		// Add Listeners and Converters
		mySetTransferHandlers();

		MouseListener myDragListener = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JComponent c = (JComponent)e.getSource();
				TransferHandler handler = c.getTransferHandler();
				handler.exportAsDrag(c, e, TransferHandler.COPY);
			}
		};
		l.addMouseListener(myDragListener);
	}

	private void mySetTransferHandlers() {
		TransferHandler th = 
			new TransferHandler((String)propertyNameCombo.getSelectedItem());
		tf.setTransferHandler(th);
		l.setTransferHandler(th);
	}
}
