package performance;

/**
 * Test string replacement done using String methods.
 */
public class PerfTimeString extends PerfTimeRegex {
	
	public String convert(String string) {
		if (string == null) {
			throw new NullPointerException("input string is null");
		}
		int index = 0, oldIndex = index;

    	StringBuffer sb = new StringBuffer();
        while ((index = string.indexOf("'", oldIndex)) != -1) {  
        	sb.append(string.substring(oldIndex, index));
    		sb.append("''");
    		oldIndex = index+1;
        }	
        sb.append(string.substring(oldIndex));
		return sb.toString();
	}

}
