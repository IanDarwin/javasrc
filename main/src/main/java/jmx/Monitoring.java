package jmx;

import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;

/** This gives information about the runtime environment.
 */
public class Monitoring {
	
	public String getOSName() {
		return System.getProperty("os.name");
	}
	
	public static void memUsage(PrintWriter out) {

		// Overall
		MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
	    MemoryUsage heap = memBean.getHeapMemoryUsage();
	    MemoryUsage nonHeap = memBean.getNonHeapMemoryUsage();
	    out.println("Heap: " + heap);
	    out.println("Non-heap: " + nonHeap);  
	    
	    // Generations details
		List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
		for (MemoryPoolMXBean mbean : memoryPoolMXBeans) {
			println(out, "Memory Group", mbean.getName());
			MemoryUsage memUsage = mbean.getUsage();  
			println(out, "Used", memUsage.getUsed());  
		    println(out, "Committed", memUsage.getCommitted());  
		    println(out, "High water", memUsage.getMax());
		    out.println();
		}
	}

	private static void println(PrintWriter out, String name, Object value) {
		out.println(String.format("%20s: %s", name, value));
	}
	
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		memUsage(out);
		out.close();
	}
}
