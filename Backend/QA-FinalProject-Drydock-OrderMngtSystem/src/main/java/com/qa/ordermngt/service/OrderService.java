package com.qa.ordermngt.service;

import com.qa.ordermngt.model.Order;
import com.qa.ordermngt.utils.IdNotFoundException;
import com.qa.ordermngt.utils.OrderNotCreatedException;

public interface OrderService {

	boolean createOrder(Order order) throws OrderNotCreatedException;

	boolean deleteOrder(Long id) throws IdNotFoundException;

}
