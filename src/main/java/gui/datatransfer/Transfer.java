import java.awt.*;
import java.awt.datatransfer.*;
import javax.swing.*;

public class Transfer extends JFrame {
	public static void main(String[] args) {
		new Transfer().setVisible(true);
	}

	public Transfer() {
		Container cp = new Box(BoxLayout.X_AXIS);
		setContentPane(cp);
		JPanel firstPanel = new JPanel();
		JComboBox propertyNameCombo = new JComboBox();
		propertyNameCombo.addItem("font");
		propertyNameCombo.addItem("text");
		propertyNameCombo.addItem("background");
		propertyNameCombo.addItem("foreground");
		firstPanel.add(propertyNameCombo);
		cp.add(firstPanel);
		JTextField tf = new JTextField(10);
		cp.add(tf);
		cp.add(Box.createGlue());
		JLabel l = new JLabel("Hello");
		cp.add(l);
		cp.add(Box.createGlue());
		JSlider stryder = new JSlider(SwingConstants.VERTICAL);
		stryder.setMinimum(10);
		stryder.setMaximum(72);
		cp.add(stryder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
	}
}
