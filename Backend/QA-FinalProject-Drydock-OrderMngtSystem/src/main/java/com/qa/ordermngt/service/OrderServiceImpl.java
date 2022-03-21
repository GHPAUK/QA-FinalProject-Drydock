package com.qa.ordermngt.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.qa.ordermngt.entitydto.OrderEntity;
import com.qa.ordermngt.model.Order;
import com.qa.ordermngt.repository.OrderRepository;
import com.qa.ordermngt.utils.IdNotFoundException;
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
			orderDto.setCost(Math.round((orderDto.getDisplacement() * orderDto.getResourcesRequired()) / 1.5f));
			repo.save(orderDto);
			return true;
		} catch (Exception e) {
			throw new OrderNotCreatedException("The order cannot be created, check the request body");
		}
	}

	@Override
	public boolean deleteOrder(Long id) throws IdNotFoundException {
		try {
			OrderEntity entityDto = repo.findById(id).get();
			repo.delete(entityDto);
			return true;
		} catch (Exception e) {
			throw new IdNotFoundException("Cannot find the specified Id");
		}
	}

	@Override
	public Order updateOrder(Long id, Order order) throws IdNotFoundException {
		try {
			OrderEntity orderDto = repo.findById(id).get();
			BeanUtils.copyProperties(order, orderDto, "id");
			orderDto.setCost(Math.round((orderDto.getDisplacement() * orderDto.getResourcesRequired()) / 1.5f));
			repo.save(orderDto);
			return order;
		} catch (Exception e) {
			throw new IdNotFoundException("Cannot find the specified Id");
		}
	}

}
