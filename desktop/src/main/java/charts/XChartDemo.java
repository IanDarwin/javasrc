package charts;

import org.knowm.xchart.*;

/**
 * XChart is a free charting library from knowm, provendors of Memristor ML chips
 * https://knowm.com for Memristor
 * https://knowm.org/open-sourcexchart
 */
public class XChartDemo {
	public static void main(String[] args) {
		double[] x = { 2, 4, 6, 8 };
		double[] y = { -2, -4, -6, -8 };

		XYChart chart = QuickChart.getChart("XChart Demo", "X", "Y", "-y(x)", x, y);

		new SwingWrapper<XYChart>(chart).displayChart();
	}
}
