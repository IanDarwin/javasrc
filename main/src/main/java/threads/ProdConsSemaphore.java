package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Producer/Consumer demo using Semaphores.
 * ProdConsSemaphore uses three `Semaphore` objects to control the
 * synchronization between the producer and consumer threads. The "mutex"
 * semaphore is used to ensure mutual exclusion when accessing the shared
 * buffer, the "empty" semaphore is used to track the number of empty
 * slots in the buffer, and the "full" semaphore is used to track the
 * number of filled slots in the buffer.
 */
public class ProdConsSemaphore {
    private Semaphore mutex = new Semaphore(1);
    private Semaphore empty = new Semaphore(10);
    private Semaphore full = new Semaphore(0);

    private int[] buffer = new int[10];
    private int in = 0;
    private int out = 0;

	/**
	 * produce() gets an empty slot in the buffer before adding an item to it,
	 *  then releases a filled slot.
	 */
    public void produce(int item) {
        try {
            empty.acquire();
            mutex.acquire();
            buffer[in] = item;
            in = (in + 1) % 10;
            System.out.println("Produced: " + item);
            mutex.release();
            full.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	/**
	 * consume() acquires a filled slot in the buffer before consuming an item from it,
	 * then releases an empty slot.
	 */
    public void consume() {
        try {
            full.acquire();
            mutex.acquire();
            int item = buffer[out];
            out = (out + 1) % 10;
            System.out.println("Consumed: " + item);
            mutex.release();
            empty.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProdConsSemaphore pc = new ProdConsSemaphore();
		ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(() -> {
            for (int i = 0; i < 20; i++) {
                pc.produce(i);
            }
        });

        pool.submit(() -> {
            for (int i = 0; i < 20; i++) {
                pc.consume();
            }
        });

		pool.shutdown();
    }
}
