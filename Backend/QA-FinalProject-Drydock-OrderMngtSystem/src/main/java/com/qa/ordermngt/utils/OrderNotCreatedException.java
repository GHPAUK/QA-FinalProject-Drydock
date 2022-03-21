package com.qa.ordermngt.utils;

public class OrderNotCreatedException extends Exception{

	public OrderNotCreatedException(String errorMessage) {
		super(errorMessage);
	}
}
