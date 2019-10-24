package threads;

import java.util.concurrent.locks.Lock;

public class LockFragment {

	Lock theLock = null;
	
	/** This file exists to hold the skeleton code at the top of Recipe 24.6 */
	public void demo() {
		theLock.lock();
		try {
			// do the work that is protected by the Lock
		} finally {
			theLock.unlock();
		}
	}
}
