import javax.naming.*;

public class DemoImplServer implements Demo extends PortableRemoteObject {
	public static void main(String[] args) { 
		System.out.println("This program is not finished yet");
	}

	private static int clientNum = 42;

	public String getNext() {
		return "You are " + ++clientNum;
	}
}
