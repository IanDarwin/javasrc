package PhoneSim;

public class PhoneCall {
	String ident = null;
	public PhoneCall(String s) {
		ident = s;
	}
	public PhoneCall() {
	}
	public void call() {
		try {
			println("Starting");
			waitaBit();
			println("Dialing");
			waitaBit();
			println("Ringing");
			waitaBit();
			if (Math.random() < .5d) {
				println("CALL FAILED");
			} else {
				println("Ringing");
				waitaBit();
				println("ANSWERED");
				waitaBit();
				println("Talking");
				waitaBit();
				if (Math.random() < .5d)
					println("Caller hung up");
				else 	println("Callee hung up");
			}
		} catch (InterruptedException e) {
			println("INTERRUPTED");
		}
	}

	public void println(String s) {
		if (ident == null)
			System.out.println(toString() + ": " + s);
		else
			System.out.println(ident + ": " + s);
	}

	public void waitaBit() throws InterruptedException {
		double d   = Math.random();
		Thread.sleep((int)(d*5000));
	}

	public static void main(String[] argv) {
		new PhoneCall().call();
	}
}
