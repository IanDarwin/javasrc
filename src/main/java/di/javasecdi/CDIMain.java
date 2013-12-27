package di.javasecdi;

import javax.enterprise.inject.Instance;

import org.jboss.weld.environment.se.Weld;

// BEGIN main
public class CDIMain {
    public static void main(String[] args) {
        final Instance<Object> weldInstance = new Weld().initialize().instance();
        weldInstance.select(ConsoleViewer.class).get().displayMessage();
    }
}
// END main
