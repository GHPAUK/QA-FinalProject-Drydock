package com.qa.ordermngt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.qa.ordermngt.entitydto.OrderEntity;
import com.qa.ordermngt.model.Order;
import com.qa.ordermngt.repository.OrderRepository;
import com.qa.ordermngt.utils.IdNotFoundException;
import com.qa.ordermngt.utils.OrderNotCreatedException;
import com.qa.ordermngt.utils.OrdersNotFoundException;

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
			orderDto.setCost();
			orderDto.setDate();
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
			e.printStackTrace();
			throw new IdNotFoundException("Cannot find the specified Id");
		}
	}

	@Override
	public Order updateOrder(Long id, Order order) throws IdNotFoundException {
		try {
			OrderEntity orderDto = repo.findById(id).get();
			BeanUtils.copyProperties(order, orderDto, "id", "date");
			orderDto.setDate(repo.findById(id).get().getDate());
			orderDto.setCost(Math.round((orderDto.getDisplacement() * orderDto.getResourcesRequired()) / 1.5f));
			order.setDate(orderDto.getDate());
			order.calcCost();
			order.setId(id);
			repo.save(orderDto);
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			throw new IdNotFoundException("Cannot find the specified Id");
		}
	}

	@Override
	public List<Order> getAllOrders() throws OrdersNotFoundException {
		try {
			List<OrderEntity> orderEntities = repo.findAll();

			List<Order> orders = orderEntities.stream()
					.map(orderEntity -> new Order(orderEntity.getId(), orderEntity.getCustomer(),
							orderEntity.getVehicleType(), orderEntity.getDisplacement(), orderEntity.isMilitary(),
							orderEntity.isWeaponised(), orderEntity.getResourcesRequired(), orderEntity.getCost(),
							orderEntity.getDate()))
					.collect(Collectors.toList());
			
			for (int i = 0; i < orderEntities.size(); i++) {
				orders.get(i).setDate(orderEntities.get(i).getDate());
			}

			for (int i = 0; i < orders.size(); i++) {
				orders.get(i).calcCost();
			}

			return orders;
		} catch (Exception e) {
			throw new OrdersNotFoundException("No orders found in database");
		}
	}

	@Override
	public Order getOrderById(Long id) throws IdNotFoundException {
		try {
			OrderEntity orderEntity = repo.findById(id).get();
			Order order = new Order();
			BeanUtils.copyProperties(orderEntity, order);
			return order;
		} catch (Exception e) {
			throw new IdNotFoundException("Cannot find the specified Id");
		}
	}

}
