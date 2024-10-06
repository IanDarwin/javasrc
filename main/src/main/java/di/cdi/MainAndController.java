package di.cdi;

import javax.enterprise.inject.Instance;

import org.jboss.weld.environment.se.Weld;

// tag::main[]
public class MainAndController {
    public static void main(String[] args) {
        final Instance<Object> weldInstance = 
			new Weld().initialize().instance();
        weldInstance.select(ConsoleViewer.class).get().displayMessage();
    }
}
// end::main[]
