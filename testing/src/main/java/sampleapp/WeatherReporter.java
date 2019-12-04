package sampleapp;

public class WeatherReporter {
	WeatherStation station;

	public void setStation(WeatherStation station) {
		this.station = station;
	}
	
	public void setUnits(Units units) {
		station.setUnits(units);
	}
	
	public double getTemperature() {
		return station.getTemperature();
	}
}
