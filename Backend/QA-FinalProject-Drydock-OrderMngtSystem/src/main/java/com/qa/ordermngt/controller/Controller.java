
package com.qa.ordermngt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.ordermngt.entity.OrderEntity;
import com.qa.ordermngt.model.Order;
import com.qa.ordermngt.service.OrderService;
import com.qa.ordermngt.utils.CustomerNotFoundException;
import com.qa.ordermngt.utils.IdNotFoundException;
import com.qa.ordermngt.utils.OrderNotCreatedException;
import com.qa.ordermngt.utils.OrdersNotFoundException;

@RestController
public class Controller {

	@Autowired
	private OrderService service;

	public Controller(OrderService service) {
		super();
		this.service = service;
	}

	@PostMapping("/order")
	public ResponseEntity<Map<String, Order>> createOrder(@RequestBody Order order)
			throws OrderNotCreatedException {
		Order created = null;
		Map<String, Order> response = new HashMap<>();
		created = service.createOrder(order);
		response.put("Created", created);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, OrderEntity>> deleteOrder(@PathVariable("id") Long id)
			throws IdNotFoundException {
		OrderEntity deleted = null;
		deleted = service.deleteOrder(id);
		Map<String, OrderEntity> response = new HashMap<>();
		response.put("Order Deleted", deleted);
		
		return ResponseEntity.ok(response);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @RequestBody Order order)
			throws IdNotFoundException {
		order = service.updateOrder(id, order);
		
		return ResponseEntity.ok(order);
	}

	@GetMapping("/getOrders")
	public ResponseEntity<List<Order>> getAllOrders() throws OrdersNotFoundException {
		List<Order> orders = null;
		orders = service.getAllOrders();
		
		return ResponseEntity.ok(orders);
	}

	@GetMapping("/getOrder/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) throws IdNotFoundException {
		Order order = null;
		order = service.getOrderById(id);
		
		return ResponseEntity.ok(order);
	}

	@GetMapping("/getOrdersByDate")
	public ResponseEntity<List<Order>> getAllOrdersByDate() throws OrdersNotFoundException {
		List<Order> orders = null;
		orders = service.getAllOrdersByDate();
		
		return ResponseEntity.ok(orders);
	}

	@GetMapping("/getOrdersByDate/{orderDate}")
	public ResponseEntity<List<Order>> getOrdersByDate(@PathVariable("orderDate") String orderDate)
			throws OrdersNotFoundException, ParseException {
		List<Order> orders = null;
		orders = service.getOrdersByDate(new SimpleDateFormat("yyyy-MM-dd").parse(orderDate));
		
		return ResponseEntity.ok(orders);
	}

	@GetMapping("/getOrdersByCost")
	public ResponseEntity<List<Order>> getAllOrdersByCost() throws OrdersNotFoundException {
		List<Order> orders = null;
		orders = service.getAllOrdersByCost();
		
		return ResponseEntity.ok(orders);
	}

	@PostMapping("/orders")
	public ResponseEntity<Map<String, Boolean>> createOrders(@RequestBody List<Order> order)
			throws OrderNotCreatedException {
		boolean created = false;
		Map<String, Boolean> response = new HashMap<>();
		created = service.createOrders(order);
		response.put("Created", created);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/deleteAll")
	public ResponseEntity<Map<String, Boolean>> deleteAllOrders() throws OrdersNotFoundException {
		boolean deleted = false;
		deleted = service.deleteAllOrders();
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted all records", deleted);
		
		return ResponseEntity.ok(response);
	}

	@GetMapping("/get/{customer}")
	public ResponseEntity<List<OrderEntity>> getOrderByQuery(@PathVariable("customer") String customer)
			throws CustomerNotFoundException {
		List<OrderEntity> orders = null;
		orders = service.getAllByCustomer(customer);
		
		return ResponseEntity.ok(orders);
	}
}
