package structure;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import misc.MyDate;

public class BeanUtilsDemo {

	/** Simple demos of what BeanUtils can do
	 * @param args
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		@SuppressWarnings("unchecked")
		Map<String,Object> m = BeanUtils.describe(c);
		printMap(m);
				
		System.out.println("----------------------------------");
		
		Map<String,Integer> dateMap = new HashMap<String,Integer>();
		dateMap.put("day", Integer.valueOf(30));
		dateMap.put("month", 1);
		dateMap.put("year", 2006);
		
		
		MyDate myDate = new MyDate();

		BeanUtils.populate(myDate, dateMap);
		
		// BeanUtils.copyProperties()
		
		System.out.println("----------------------------------");
		
		@SuppressWarnings("unchecked")
		Map<String, Object> descr = PropertyUtils.describe(myDate);
		printMap(descr);

		System.out.println(myDate);
	}

	/**
	 * @param m
	 */
	private static void printMap(Map<String, Object> map) {
		map.forEach((k,v) -> {
			System.out.println(v + "-->" + v.toString() + "(" + v.getClass().getName() + ")");
		});
	}

}
