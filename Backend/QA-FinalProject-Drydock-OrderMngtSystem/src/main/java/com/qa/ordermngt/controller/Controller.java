package com.qa.ordermngt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.qa.ordermngt.model.Order;
import com.qa.ordermngt.service.OrderService;
import com.qa.ordermngt.utils.OrderNotCreatedException;

@RestController
public class Controller {

	@Autowired
	private OrderService service;

	public Controller(OrderService service) {
		super();
		this.service = service;
	}

	@PostMapping("/order")
	public ResponseEntity<Map<String, Boolean>> createOrder(@RequestBody Order order) throws OrderNotCreatedException {
		boolean created = false;
		Map<String, Boolean> response = new HashMap<>();
		created = service.createOrder(order);
		response.put("Created", created);

		return ResponseEntity.ok(response);
	}

}