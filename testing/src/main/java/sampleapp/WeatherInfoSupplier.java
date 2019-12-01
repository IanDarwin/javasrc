package sampleapp;

public class WeatherInfoProvider {

	private WeatherStation station;

	private Units units;

	void setWeatherStation(WeatherStation station) {
		this.station = station;
	}

	public void setUnits(Units units) {
		this.units = units;
		station.setUnits(units);
	}

	public double getTemperature() {
		return station.getTemperature();
	}
}
