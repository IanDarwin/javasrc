package pool;

public class PoolDemo implements PoolFactory {

	public static void main(String[] args) throws Exception {
		PoolDemo pd = new PoolDemo();
		Pool p = new Pool(pd, 5, 20, 3);
		runServer(p);
	}

	public Object getInstance() {
		return new O();
	}

	public class O implements Runnable {
		public O() { }
		public void run() {
			try {
				System.out.println("Thread" + " napping");
				Thread.sleep((int)(10000 * Math.random()));
				System.out.println("Thread" + " awake");
			} catch (InterruptedException ex) {
				System.err.println("YAWN");
				System.exit(1);
			}
		}
	}

	static void runServer(Pool p) {
		for (;;) {
			Object r = p.take();
			new Thread(((Runnable)r)).start();
			p.release((O)r);
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
			}
		}
	}
}
