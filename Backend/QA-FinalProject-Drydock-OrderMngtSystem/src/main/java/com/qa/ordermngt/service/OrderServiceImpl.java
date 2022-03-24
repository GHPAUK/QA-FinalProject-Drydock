package com.qa.ordermngt.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.qa.ordermngt.entity.OrderEntity;
import com.qa.ordermngt.model.Order;
import com.qa.ordermngt.repository.OrderRepository;
import com.qa.ordermngt.utils.CustomerNotFoundException;
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
	public OrderEntity createOrder(Order order) throws OrderNotCreatedException {
		try {
			OrderEntity toSave = new OrderEntity();
			BeanUtils.copyProperties(order, toSave);
			toSave.setCost();
			toSave.setDate();
			order.setDate();
			repo.save(toSave);
			return toSave;
		} catch (Exception e) {
			throw new OrderNotCreatedException("The order cannot be created, check the request body");
		}
	}

//	@Override
//	public boolean deleteOrder(Long id) throws IdNotFoundException {
//		try {
//			OrderEntity entityDto = repo.findById(id).get();
//			repo.delete(entityDto);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new IdNotFoundException("Cannot find the specified Id");
//		}
//	}

	@Override
	public OrderEntity deleteOrder(Long id) throws IdNotFoundException {
		try {
			Optional<OrderEntity> toDelete = this.repo.findById(id);
			this.repo.deleteById(id);
			return toDelete.orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IdNotFoundException("Cannot find the specified Id");
		}
	}

	@Override
	public Order updateOrder(Long id, Order order) throws IdNotFoundException {
		try {
			OrderEntity found = repo.findById(id).get();
			BeanUtils.copyProperties(order, found, "id", "date");
			found.setDate(repo.findById(id).get().getDate());
			found.setCost(Math.round((found.getDisplacement() * found.getResourcesRequired()) / 1.5f));
			order.setDate(found.getDate());
			order.calcCost();
			order.setId(id);
			repo.save(found);
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
			order.calcCost();
			return order;
		} catch (Exception e) {
			throw new IdNotFoundException("Cannot find the specified Id");
		}
	}

	@Override
	public List<Order> getAllOrdersByDate() throws OrdersNotFoundException {
		try {
			List<OrderEntity> orderEntities = repo.findAllByOrderByDateAsc();

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
	public List<Order> getOrdersByDate(Date orderDate) throws OrdersNotFoundException {
		try {
			List<OrderEntity> orderEntities = repo.findOrdersByDate(orderDate);

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
			e.printStackTrace();
			throw new OrdersNotFoundException("No orders found in database");
		}
	}

	@Override
	public List<Order> getAllOrdersByCost() throws OrdersNotFoundException {
		try {
			List<OrderEntity> orderEntities = repo.findAllByOrderByCostDesc();

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
			e.printStackTrace();
			throw new OrdersNotFoundException("No orders found in database");
		}
	}

	@Override
	public boolean createOrders(List<Order> order) throws OrderNotCreatedException {
		try {
			for (int i = 0; i < order.size(); i++) {
				OrderEntity toSave = new OrderEntity();
				BeanUtils.copyProperties(order.get(i), toSave, "id");
				toSave.setCost();
				toSave.setDate();
				repo.save(toSave);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OrderNotCreatedException("The order cannot be created, check the request body");
		}
	}

	@Override
	public boolean deleteAllOrders() throws OrdersNotFoundException {
		try {
			repo.deleteAll();
			return true;
		} catch (Exception e) {
			throw new OrdersNotFoundException("No orders found in database");
		}
	}

	@Override
	public List<OrderEntity> getAllByCustomer(String customer) throws CustomerNotFoundException {
		try {
			List<OrderEntity> orders = null;
			orders = repo.findAllByCustomer(customer);
			return orders;
		} catch (Exception e) {
			throw new CustomerNotFoundException("No field found in database");
		}
	}

}
