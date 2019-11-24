package jmx;

public class MyController implements MyControllerMBean {
	
	private int resetCount;
	/* (non-Javadoc)
	 * @see jmx.MyControllerMBean#reboot()
	 */
	public void reset() {
		System.out.println("Scheduling application reset...");
		++resetCount;
	}
	/* (non-Javadoc)
	 * @see jmx.MyControllerMBean#shutDown()
	 */
	public void shutDown() {
		System.out.println("Scheduling application shutdown...");
		System.exit(0);
	}
	/* (non-Javadoc)
	 * @see jmx.MyControllerMBean#getRebootCount()
	 */
	public int getResetCount() {
		return resetCount;
	}
	/* (non-Javadoc)
	 * @see jmx.MyControllerMBean#setRebootCount(int)
	 */
	public void setResetCount(int rebootCount) {
		this.resetCount = rebootCount;
	}

}
