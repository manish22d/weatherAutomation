package com.weather.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.weather.constant.Constants;

public class CityWeatherPage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(css = ".cur-con-weather-card__body .temp")
	WebElement currentTemp;

	public CityWeatherPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
		PageFactory.initElements(driver, this);
	}

	public int currentWeather() {
		wait.until(ExpectedConditions.visibilityOf(currentTemp));

		return Integer.parseInt(currentTemp.getText().replaceAll("\\D+", ""));
	}
}
