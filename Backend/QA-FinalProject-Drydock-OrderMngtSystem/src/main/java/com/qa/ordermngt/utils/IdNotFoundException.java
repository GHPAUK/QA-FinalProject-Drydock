package com.qa.ordermngt.utils;

// Suppressing serialisation warning because there is no need to do so
@SuppressWarnings("serial")
public class IdNotFoundException extends Exception{

	public IdNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
