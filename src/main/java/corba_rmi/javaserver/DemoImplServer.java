public class DemoImplServer
	implements Demo
	extends PortableRemoteObject
{
	public static void main(String[] args) { 
	}

	private static int clientNum = 42;

	public String getNext() {
		return "You are " + ++clientNum;
	}
}
