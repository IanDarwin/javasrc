import java.io.*;
import java.util.*;

public class TMTimerUtil {

	/* Parse a Properties file like this:
	 * <PRE>
	 * # Color choices for local display
	 * # Not implemented yet.
	 * # colors=green yellow red
	 * # List of presets. Format is:
	 * # arbitrary_label=m:ss m:ss m:ss
	 * 30,45,60=0:30 0:45 1:00
	 * 5m,6m,7m=5:00 6:00 7:00
	 * </PRE>
	 * return an Iterator (HashMap?) whose keys are the labels
	 * and whose values are an array of ints with the seconds.
	 */
	 public static Iterator parseProps(Properties p) {
		HashMap h = new HashMap();
	 	Enumeration it = p.keys();
		while (it.hasMoreElements()) {
			String key = (String)it.nextElement();
			String val = (String)p.getProperty(key);
			System.out.println("Key " + key + " = " + val);
			int[] data = new int[3];
			// TODO parse val, store in data
			StringTokenizer st = new StringTokenizer(val);
			int ix = 0;
			while (st.hasMoreElements()) {
				String t = (String)st.nextElement();
				data[ix++] = mmssToInt(t);
			}
			h.put(key, data);
		}
		return h.values().iterator();
	}

	public static int mmssToInt(String t) {
		int i;
		if ((i = t.indexOf(':')) < 0)
			return Integer.parseInt(t);
		else {
			String mm = t.substring(0, i);
			String ss = t.substring(i+1);
			// System.out.println(mm + "--" + ss);
			int nSec = Integer.parseInt(mm) * 60 + Integer.parseInt(ss);
			return nSec;
		}
	}

	public static void getProperties(Properties p, String progname) {
		String fileName = (progname + ".properties");
		try {
			InputStream is = new FileInputStream(fileName);
			p.load(is);
			is.close();
		} catch (IOException e) {
			System.err.println("Cannot read " + fileName);
			return;
		}
	}

	public static void main(String[] args) {
		Properties p = new Properties();
		TMTimerUtil.getProperties(p, "TMTimer");
		TMTimerUtil.parseProps(p);
	}
}
