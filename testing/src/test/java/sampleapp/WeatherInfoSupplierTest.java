package sampleapp;

// tag::imports[]
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
// end::imports[]

import org.junit.Before;
import org.junit.Test;

public class WeatherInfoSupplierTest {

	// The main class; what we are testing
	WeatherReporter reporter = new WeatherReporter();

	// Its dependency, a WeatherStation, which will
	// be mocked up for the tests.
	WeatherStation mockStation;

	static int testCount;

	/** A hand-coded mock WeatherStation */
	class MyMockStation implements WeatherStation {

		private Units units = Units.SI; // "Systeme Internationale"

		@Override
		public void setUnits(Units units) {
			this.units = units;
		}

		@Override
		public double getTemperature() {
			switch (units) {
				case SI: return 19.0;
				case US: return 68.0;
				default: throw new RuntimeException();
			}
		}
	}

	@Test
	public void testGetTemperatureDegC() {
		reporter.setStation(mockStation = new MyMockStation());
		reporter.setUnits(Units.SI);
		// 19.0 C, an OK temperature
		assertEquals(19.0, reporter.getTemperature(), 0.01);
	}

	@Test
	public void testGetTemperatureDegF() {
		reporter.setStation(mockStation = new MyMockStation());
		reporter.setUnits(Units.US);
		// 68 F, an OK temperature
		assertEquals(68.0, reporter.getTemperature(), 0.01);
	}

	@Test
	public void showExpectations() {
		// tag::main[]
		reporter.setStation(mockStation = mock(WeatherStation.class));
		when(mockStation.getTemperature()).thenReturn(19.0);
		if (testCount == 0) { // hard-coded mock, no expectations
			return;
		}
		reporter.getTemperature(); // Delegates to station::getTemperature
		verify(mockStation, atLeastOnce()).getTemperature();
		// end::main[]
	}
}
