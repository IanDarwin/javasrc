package com.darwinsys.charts;

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
