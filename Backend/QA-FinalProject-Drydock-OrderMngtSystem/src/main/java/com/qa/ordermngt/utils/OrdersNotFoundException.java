package com.qa.ordermngt.utils;

//Suppressing serialisation warning because there is no need to do so
@SuppressWarnings("serial")
public class OrdersNotFoundException extends Exception {
	public OrdersNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
