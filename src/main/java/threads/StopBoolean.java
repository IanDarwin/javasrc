public class StopBoolean extends Thread {
	protected boolean done = false;
	public void run() {
		while (!done) {
			System.out.println("StopBoolean running");
			try {
				sleep(720);
			} catch (InterruptedException ex) {
				// nothing to do 
			}
		}
		System.out.println("StopBoolean finished.");
	}
	public void shutDown() {
		done = true;
	}
}
