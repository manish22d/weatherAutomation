package com.weather.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.weather.comparator.City;
import com.weather.comparator.Compare;
import com.weather.exceptions.InvalidSourceException;
import com.weather.tests.common.BaseTest;

public class ComparatorTest extends BaseTest {
	private static Logger log = LoggerFactory.getLogger(ComparatorTest.class);

	@Test
	public void compareCityTemp() {

		City ui = new City();
		ui.setCityName(property.getProperty("test.city"));
		cityPage = homepage.searchCity(ui.getCityName());
		ui.setTemp(cityPage.currentWeather());

		City api = new City();
		api.setCityName(property.getProperty("test.city"));
		api.setTemp(weatherAPI.getCityTempreture(api.getCityName()));

		System.out.println(ui);
		System.out.println(api);
		System.out.println(ui.compareTo(api));
	}

	@Test(expectedExceptions = InvalidSourceException.class)
	public void compareDifferentCity() throws InvalidSourceException {
		City city1 = new City();
		city1.setCityName(property.getProperty("test.city1"));
		city1.setTemp(weatherAPI.getCityTempreture(city1.getCityName()));

		City city2 = new City();
		city2.setCityName(property.getProperty("test.city2"));
		city2.setTemp(weatherAPI.getCityTempreture(city2.getCityName()));

		log.info("city 1: ", city1);
		log.info("city 2: ", city2);
		System.out.println("Tempreture difference between cities : "+ city1.compareTo(city2));

		Compare.compareTemp(city1, city2);
	}

}
