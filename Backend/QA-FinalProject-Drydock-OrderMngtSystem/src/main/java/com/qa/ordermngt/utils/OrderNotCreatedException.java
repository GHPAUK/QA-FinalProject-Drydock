package com.qa.ordermngt.utils;

//Suppressing serialisation warning because there is no need to do so
@SuppressWarnings("serial")
public class OrderNotCreatedException extends Exception{

	public OrderNotCreatedException(String errorMessage) {
		super(errorMessage);
	}
}
