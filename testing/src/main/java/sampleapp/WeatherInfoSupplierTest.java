package sampleapp;

import org.junit.*;

import static org.junit.Assert.*;

public class WeatherInfoSupplierTest {

	WeatherReporter reporter;

	WeatherStation mockStation;

	static int testCount;

	@Before
	public void setup() {
		// First use hard-coded mock station, next time use Mockito
		if (testCount++%2 == 0) {
			mockStation = new MyMockStation();
		} else {
			mockStation = mock(WeatherStation);
			mockStation.when(getTemperature()).thenReturn(22.2);
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
	public void testGetTemperatureDegC() {
		reporter.setUnits(Units.US);
		// 68 F, an OK temperature
		assertEquals(68.0, reporter.getTemperature(), 0.01);
	}
}
