package com.weather.comparator;

import com.weather.exceptions.InvalidSourceException;

public class Compare {

	public static void compareTemp(City c1, City c2) throws InvalidSourceException {
		if (c1.compareTo(c2) > 2)
			throw new InvalidSourceException("City tempreture is greater Than thresshold temp");
	}
}
