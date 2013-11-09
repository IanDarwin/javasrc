package di.spring;

import javax.annotation.Resource;

public class ConsoleViewer implements View {

	Model messageProvider;
	
	@Override
	public void displayMessage() {
		System.out.println(messageProvider.getMessage());
	}

	@Resource(name="messageProvider")
	public void setModel(Model messageProvider) {
		this.messageProvider = messageProvider;
	}

}
