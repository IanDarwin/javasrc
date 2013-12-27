package di.spring;

import javax.annotation.Resource;
import javax.swing.JOptionPane;

import di.View;

public class SwingViewer implements View {

	Model messageProvider;
	
	@Override
	public void displayMessage() {
		JOptionPane.showMessageDialog(null, messageProvider.getMessage());
	}

	@Resource(name="messageProvider")
	public void setModel(Model messageProvider) {
		this.messageProvider = messageProvider;
	}

}
