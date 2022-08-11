package sampleapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class WeatherInfoSupplierTest {

	WeatherReporter reporter = new WeatherReporter();

	WeatherStation mockStation;

	static int testCount;

	class MyMockStation implements WeatherStation {

		private Units units;

		@Override
		public void setUnits(Units units) {
			this.units = units;
		}

		@Override
		public double getTemperature() {
			switch (units) {
				case SI: return 22.2;
				case US: return 68.0;
				default: throw new RuntimeException();
			}
		}
	}

	@Before
	public void setup() {
		// First use hard-coded mock station, next time use Mockito
		if (testCount++%2 == 0) {
			mockStation = new MyMockStation();
		} else {
			mockStation = mock(WeatherStation.class);
			when(mockStation.getTemperature()).thenReturn(68.0);
		}
		reporter.setStation(mockStation);
	}

	@Test
	public void testGetTemperatureDegC() {
		reporter.setUnits(Units.SI);
		// 22.2 C, an OK temperature
		assertEquals(22.2, reporter.getTemperature(), 0.01);
	}

	@Test
	public void testGetTemperatureDegF() {
		reporter.setUnits(Units.US);
		// 68 F, an OK temperature
		assertEquals(68.0, reporter.getTemperature(), 0.01);
	}
}
