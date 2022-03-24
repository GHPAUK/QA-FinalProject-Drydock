package com.qa.ordermngt.utils;

//Suppressing serialisation warning because there is no need to do so
@SuppressWarnings("serial")
public class CustomerNotFoundException extends Exception {
	public CustomerNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
