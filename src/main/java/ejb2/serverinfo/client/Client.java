import com.darwinsys.ejb.*;
import javax.naming.*;
import java.rmi.*;

public class Client {

	public static void main(String[] args) throws Exception {

		Context ctx = new InitialContext();

		println("Lookup h1");
		ServInfoHome h1 = (ServInfoHome)ctx.lookup("ServInfo1");
		println("h1 = " + h1.getClass());

		println("Lookup h2");
		ServInfoHome h2 = (ServInfoHome)ctx.lookup("ServInfo2");
		println("h2 = " + h2.getClass());

		ServInfo c1 = h1.create();

		ServInfo c2 = h2.create();

		println("c1 sayeth: " + c1.getEnv("duplicate"));
		println("c2 sayeth: " + c2.getEnv("duplicate"));
	}

	static void println(String s) { System.out.println(s); }
}
