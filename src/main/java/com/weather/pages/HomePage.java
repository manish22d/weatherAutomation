package com.weather.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.weather.constant.Constants;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(name = "query")
	WebElement searchField;

	@FindBy(css = "div.results-container div")
	List<WebElement> searchResult;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
		PageFactory.initElements(driver, this);
	}

	public CityWeatherPage searchCity(String cityName) {
		wait.until(ExpectedConditions.elementToBeClickable(searchField));
		searchField.click();
		searchField.sendKeys(cityName);

		wait.until(ExpectedConditions.visibilityOfAllElements(searchResult));
		searchResult.stream().filter(city -> city.getText().toLowerCase().contains(cityName.toLowerCase())).findFirst()
				.orElse(null).click();
		return new CityWeatherPage(driver);
	}

}
