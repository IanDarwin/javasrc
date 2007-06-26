package jmx;

public interface MyControllerMBean {

	public void reset();

	public void shutDown();

	public int getResetCount();

	public void setResetCount(int rebootCount);

}