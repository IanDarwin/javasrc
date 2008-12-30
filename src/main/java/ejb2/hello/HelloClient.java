package ejb2.hello;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class HelloClient {
        public static void main(String[] args) {

                try {
                        // Properties from jndi.properties in current directory
                        Context initial = new InitialContext();

                        System.out.println("Getting Home Reference...");
                        Object objref = initial.lookup("HelloEJB");
                        HelloHome home =
                                (HelloHome)PortableRemoteObject.narrow(objref,
                                        HelloHome.class);

                        // Call the EJBHome's create() method to get the EJBObject
                        System.out.println("Creating EJB Reference...");
                        Hello myHello = home.create();

                        System.out.println("Calling EJB Method...");
                        String response = myHello.sayHello("Ian");

                        System.out.println("Result was: " + response);

                } catch (Exception ex) {
                        System.err.println("Caught an unexpected exception!");
                        ex.printStackTrace();
                }

        }
}
