package di.spring;

import javax.annotation.Resource;
import javax.inject.Named;

import di.Model;
import di.View;

// tag::main[]
@Named("myView")
public class ConsoleViewer implements View {

	Model messageProvider;
	
	@Override
	public void displayMessage() {
		System.out.println(messageProvider.getMessage());
	}

	@Resource(name="myModel")
	public void setModel(Model messageProvider) {
		this.messageProvider = messageProvider;
	}

}
// end::main[]
