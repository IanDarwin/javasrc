import java.io.IOException;

public class ThreadStoppers {
	public static void main(String[] args) 
	throws InterruptedException, IOException {
		Thread t1 = new StopBoolean();
		Thread t2 = new StopClose();
		t1.start();
		t2.start();
		Thread.sleep(1000*5);
		((StopBoolean)t1).shutDown();
		((StopClose  )t2).shutDown();
	}
}

