package threads;

import java.util.concurrent.locks.*;

public class LockFragment {

	/** This file exists to hold the skeleton code at the top of Recipe 24.6 */
	public void demo() {
		Lock theLock = null;
		try {
			theLock.lock();
			// do the work that is protected by the Lock
		} finally {
			theLock.unlock();
		}
	}
}
