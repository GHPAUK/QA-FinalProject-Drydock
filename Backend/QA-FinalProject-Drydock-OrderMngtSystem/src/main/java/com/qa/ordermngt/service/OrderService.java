package com.qa.ordermngt.service;

import java.util.List;

import com.qa.ordermngt.model.Order;
import com.qa.ordermngt.utils.IdNotFoundException;
import com.qa.ordermngt.utils.OrderNotCreatedException;
import com.qa.ordermngt.utils.OrdersNotFoundException;

public interface OrderService {

	boolean createOrder(Order order) throws OrderNotCreatedException;

	boolean deleteOrder(Long id) throws IdNotFoundException;

	Order updateOrder(Long id, Order order) throws IdNotFoundException;

	List<Order> getAllOrders() throws OrdersNotFoundException;

	Order getOrderById(Long id) throws IdNotFoundException;

	
}
