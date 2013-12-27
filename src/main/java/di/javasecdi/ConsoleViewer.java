package di.javasecdi;

import javax.inject.Inject;

// BEGIN main
public class ConsoleViewer implements View {
    @Inject @MyModel
    private String message;
    
    @Override
    public void displayMessage() {
        System.out.println(message);
    }
}
// END main
