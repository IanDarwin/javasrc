package netwatch;

import java.awt.*;
import javax.swing.*;

public class ProtoDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	JComboBox<String> protoBox, portBox;

	public ProtoDialog(Frame f, String t) {
		super(f, t, true);

		Container cp = getContentPane();
		cp.setLayout(new GridLayout(2,2));
		cp.add(new JLabel("Protocol:", JLabel.RIGHT));
		cp.add(protoBox = new JComboBox<>());
		protoBox.addItem("Ping");
		protoBox.addItem("RMI");
		protoBox.addItem("CORBA");
		protoBox.addItem("WWW");
		protoBox.setEditable(true);
		cp.add(new JLabel("Port:", JLabel.RIGHT));
		cp.add(portBox = new JComboBox<>());
		portBox.addItem("0");
		portBox.addItem("1099");
		portBox.addItem("28735");
		portBox.setEditable(true);
		pack();
	}

	public String getProto() {
		return protoBox.getSelectedItem().toString();
	}
	public String getPort() {
		return portBox.getSelectedItem().toString();
	}
	public static void main(String[] args) {
		new ProtoDialog(new Frame("ProtoDialog dummy parent"),
			"ProtoDialog Test").setVisible(true);
	}
}
