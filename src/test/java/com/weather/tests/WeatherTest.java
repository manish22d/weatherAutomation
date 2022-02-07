package com.weather.tests;

import org.testng.annotations.Test;

import com.weather.pages.HomePage;
import com.weather.tests.common.BaseTest;

public class WeatherTest extends BaseTest {
	

	@Test
	public void verifyWeatherOfCity() {
		homepage = new HomePage(driver);
		cityPage = homepage.searchCity("mysore");

		System.out.println(cityPage.currentWeather());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void verifyWeatherOfCityFromAPI() {
		System.out.println(weatherAPI.getCityTempreture(property.getProperty("test.city")));
	}
}
