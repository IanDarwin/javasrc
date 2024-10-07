package threads;

import java.util.concurrent.*;

/** Simple demo of synchronized method.
 */
public class ArrayWriter {
	private static final int HOWMANY = 1000;
    private static int[] array;
	private static ExecutorService pool = Executors.newFixedThreadPool(2);

    static Runnable runBad = () -> {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] + i;
        }
    };

	static Runnable runGood = () -> {
		synchronized(array) {
			for (int i = 0; i < array.length; i++) {
				array[i] = array[i] + i;
			}
		}
	};

    public static void main(String[] args) throws Exception {
		process("runGood", runGood);
		process("runBad", runBad);
	}

	static void process(String name, Runnable run) throws Exception {
		System.out.println("Starting: " + name);
		array = new int[HOWMANY];
        var t1 = pool.submit(runBad);
        var t2 = pool.submit(runBad);
		t1.get();
		t2.get();
		for (int i = 0; i < array.length; i++) {
			if (array[i] != 2 * i) {
				System.out.printf("%d found at offset %d\n", array[i], i);
			}
		}
	}
}
