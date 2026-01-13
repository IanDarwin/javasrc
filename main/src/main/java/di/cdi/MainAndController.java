package di.cdi;

import org.jboss.weld.environment.se.Weld;

// tag::main[]
public class MainAndController {
    public static void main(String[] args) {
        var weldInstance =  new Weld().initialize().instance();
        weldInstance.select(ConsoleViewer.class).get().displayMessage();
    }
}
// end::main[]
