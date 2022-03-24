package com.qa.ordermngt.service;

import java.util.Date;
import java.util.List;

import com.qa.ordermngt.entity.OrderEntity;
import com.qa.ordermngt.model.Order;
import com.qa.ordermngt.utils.CustomerNotFoundException;
import com.qa.ordermngt.utils.IdNotFoundException;
import com.qa.ordermngt.utils.OrderNotCreatedException;
import com.qa.ordermngt.utils.OrdersNotFoundException;

public interface OrderService {

	OrderEntity createOrder(Order order) throws OrderNotCreatedException;

	OrderEntity deleteOrder(Long id) throws IdNotFoundException;

	Order updateOrder(Long id, Order order) throws IdNotFoundException;

	List<Order> getAllOrders() throws OrdersNotFoundException;

	Order getOrderById(Long id) throws IdNotFoundException;

	List<Order> getAllOrdersByDate() throws OrdersNotFoundException;
	
	List<Order> getOrdersByDate(Date date) throws OrdersNotFoundException;
	
	List<Order> getAllOrdersByCost() throws OrdersNotFoundException;

	boolean createOrders(List<Order> order) throws OrderNotCreatedException;

	boolean deleteAllOrders() throws OrdersNotFoundException;

	List<OrderEntity> getAllByCustomer(String customer) throws CustomerNotFoundException;
}
