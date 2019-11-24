package di.cdi;

import javax.inject.Inject;

import di.View;

// tag::main[]
public class ConsoleViewer implements View {
    @Inject @MyModel
    private String message;
    
    @Override
    public void displayMessage() {
        System.out.println(message);
    }
}
// end::main[]
