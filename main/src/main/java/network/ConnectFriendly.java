package network;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;

/* Client with error handling */
// tag::main[]
public class ConnectFriendly {
  public static void main(String[] argv) {
    String serverName = argv.length == 1 ? argv[0] : "localhost";
    int tcpPort = 80;
    try (Socket sock = new Socket(serverName, tcpPort)) {

      /* If we get here, we can read and write on the socket. */
      System.out.println(" *** Connected to " + serverName  + " ***");

      /* Do some I/O here... */

    } catch (UnknownHostException e) {
      System.err.println(serverName + " Unknown host");
      System.exit(1);
    } catch (NoRouteToHostException e) {
      System.err.println(serverName + " Unreachable" );
      System.exit(1);
    } catch (ConnectException e) {
      System.err.println(serverName + " Connection Refused");
      System.exit(1);
    } catch (java.io.IOException e) {
      System.err.println(serverName + ' ' + e);
      System.exit(1);
    }
  }
}
// end::main[]
