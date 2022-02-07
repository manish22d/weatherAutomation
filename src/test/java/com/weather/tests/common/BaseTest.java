package com.weather.tests.common;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.weather.api.WeatherAPICaller;
import com.weather.core.WebDriverManager;
import com.weather.listeners.TestListener;
import com.weather.pages.CityWeatherPage;
import com.weather.pages.HomePage;
import com.weather.utils.PropertyUtils;

@Listeners(TestListener.class)
public class BaseTest {
	protected WebDriver driver;
	public Properties property;
	protected String appUrl;

	public WeatherAPICaller weatherAPI;
	public HomePage homepage;
	public CityWeatherPage cityPage;

	@BeforeSuite
	public void loadConfig() {
		property = PropertyUtils.getProperty();
		appUrl = property.getProperty("app.url");

	}

	@BeforeClass(alwaysRun = true)
	public void classSetup(ITestContext context) {
		driver = WebDriverManager.getDriver();
		driver.navigate().to(appUrl);
		homepage = new HomePage(driver);
		context.setAttribute("webdriver", driver);
		weatherAPI = new WeatherAPICaller(property);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
