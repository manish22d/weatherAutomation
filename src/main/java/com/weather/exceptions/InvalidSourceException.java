package com.weather.exceptions;

@SuppressWarnings("serial")
public class InvalidSourceException extends Exception {

	public InvalidSourceException(String msg) {
		super(msg);
	}
}
