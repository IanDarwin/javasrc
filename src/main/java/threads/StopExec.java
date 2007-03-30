package threads;

/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2007.
 * All rights reserved. Software written by Ian F. Darwin and others.
 * $Id$
 */

import java.io.IOException;

/** Stop a thread by killing an executed program.
 */
public class StopExec extends Thread {

	private static final int MAX_TIMEOUT = 10;
	private Process proc;
	private int timeToRunProcess;

	public StopExec(int timeToRunProcess) {
		System.out.printf("Creating a %d-second process%n", timeToRunProcess);
		this.timeToRunProcess = timeToRunProcess;
	}

	/** Thread.run() method: run the process, which will take "timeToRunProcess" seconds.
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		try {
			System.out.println("StopExec.run(): starting process");
			// Use of sleep with a number is to simulate different run times of a process
			proc = Runtime.getRuntime().exec("sleep " + timeToRunProcess);
			proc.waitFor();
			System.out.println("StopExec.run(): process terminated, exit status " + proc.waitFor());
		} catch (IOException ex) {
			System.out.println("StopExec terminating: " + ex);
		} catch (InterruptedException e) {
			System.out.println("StopExec.run(): Was interrupted");
		}
	}

	/**
	 * Kill the process, if it was taking too long.
	 */
	public void shutDown() {
		if (proc != null) {
			System.out.println("StopExec.shutDown(): destroying process " + proc);
			proc.destroy();
		}
		System.out.println("StopExec.shutDown() completed");
	}

	/**
	 * Create a series of instances with runtimes less than, equal to, and greater than, MAX_TIMEOUT.
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		for (int timeToRunProcess : new int[]{5,10,15}) {
			StopExec t = new StopExec(timeToRunProcess);
			t.start();
			Thread.sleep(1000*MAX_TIMEOUT);
			// If thread is still alive, consider it hung, and kill its process.
			if (t.isAlive()) {
				System.err.println("HUNG PROCESS, killing it.");
				t.shutDown();
			}
		}
	}
}
