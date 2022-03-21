package com.qa.ordermngt.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.qa.ordermngt.entitydto.OrderEntity;
import com.qa.ordermngt.model.Order;
import com.qa.ordermngt.repository.OrderRepository;
import com.qa.ordermngt.utils.OrderNotCreatedException;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository repo;

	public OrderServiceImpl(OrderRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public boolean createOrder(Order order) throws OrderNotCreatedException {
		try {
			OrderEntity orderDto = new OrderEntity();
			BeanUtils.copyProperties(order, orderDto);
			orderDto.setCost((orderDto.getDisplacement() * orderDto.getResourcesRequired()) / 1.5f);
			repo.save(orderDto);
			return true;
		} catch (Exception e) {
			throw new OrderNotCreatedException("The order cannot be created, check the request body");
		}
	}
	
	

}
