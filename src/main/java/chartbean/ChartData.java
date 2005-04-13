package ChartBean;

/** Simple POJO for chart data
 */
public class ChartData {
	String name;
	int value;

	ChartData(int i, String s) {
		name = s;
		value = i;
	}
	ChartData(String s, int i) {
		this(i, s);
	}
}
