package com.weather.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpStatus;

public class WeatherAPICaller {
	Properties prop;
	public APIHelper api;
	protected String apiUrl;
	protected String basePath;
	protected String apiKey;

	public WeatherAPICaller(Properties prop) {
		this.prop = prop;
		apiUrl = prop.getProperty("api.baseURI");
		basePath = prop.getProperty("api.basePath");
		apiKey = prop.getProperty("api.apiKey");
		api = new APIHelper();
		api.setBaseURI(apiUrl);
		api.setBasePath(basePath);
		api.initiateRequest();
		api.setAPIEndpoint("/weather");
	}

	public int getCityTempreture(String city) {

		Map<String, String> queryParam = new HashMap<>();
		queryParam.put("q", city);
		queryParam.put("units", "metric");
		queryParam.put("appid", apiKey);
		api.setQueryParam(queryParam);
		api.getResource();
		assertThat(api.response.getStatusCode(), is(equalTo(HttpStatus.SC_OK)));

		return (int) Math.round(api.response.jsonPath().getDouble("main.temp"));
	}

}
