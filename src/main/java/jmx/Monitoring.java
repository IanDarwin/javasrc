package webmonitoring;

import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.Iterator;

/** This gives information about the runtime environment.
 */
public class WebMonitoring {
	
	public String getOSName() {
		return System.getProperty("os.name");
	}
	
	public void memUsage(PrintWriter out) {
		Iterator it = ManagementFactory.getMemoryPoolMXBeans().iterator();  
		while(it.hasNext()){  
			MemoryPoolMXBean mbean = (MemoryPoolMXBean) it.next();
			println(out, "Memory Group", mbean.getName());
			MemoryUsage memUsage = mbean.getUsage();  
			println(out, "Used", memUsage.getUsed());  
		    println(out, "Committed", memUsage.getCommitted());  
		    println(out, "High water", memUsage.getMax());  
		}
	}

	private void println(PrintWriter out, String name, Object value) {
		out.println(String.format("%20s: %s", name, value));
	}
}
