package com.qa.ordermngt.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.ordermngt.entitydto.OrderEntity;
import com.qa.ordermngt.model.Order;
import com.qa.ordermngt.repository.OrderRepository;
import com.qa.ordermngt.utils.IdNotFoundException;
import com.qa.ordermngt.utils.OrderNotCreatedException;
import com.qa.ordermngt.utils.OrdersNotFoundException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Sql(scripts = { "classpath:init_test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dev")
public class ServiceTest {

	@MockBean
	private OrderRepository repo;

	@Autowired
	private OrderService service;

	// Test Objects
	// Entities
	OrderEntity orderEnt1 = new OrderEntity(1l, "TestCustomer1", "TestVehicle1", 100, true, true, 50, 3333);
	OrderEntity orderEnt2 = new OrderEntity("TestCustomer2", "TestVehicle2", 100, true, true, 50, 3333);
	
	// Entity List
	List<OrderEntity> ordersListEnt = List.of(orderEnt1, orderEnt1);
	
	// Models
	Order order1 = new Order("TestCustomer1", "TestVehicle1", 100, true, true, 50, 3333,
			Calendar.getInstance().getTime());
	Order orderId1 = new Order(1l, "TestCustomer1", "TestVehicle1", 100, true, true, 50, 3333,
			Calendar.getInstance().getTime());
	Order orderId2 = new Order(1l, "TestCustomer1", "TestVehicle1", 100, true, true, 50, 3333, null);
	Order order2 = new Order("TestCustomer1", "TestVehicle1", 100, true, true, 50, 3333,
			Calendar.getInstance().getTime());

	// Model List
	List<Order> ordersList = List.of(orderId2, orderId2);
	
	
	@Test
	public void createOrderTest() throws OrderNotCreatedException {
		// Act
		Mockito.when(repo.save(orderEnt1)).thenReturn(orderEnt1);
		orderEnt1.setCost(3333);
		orderEnt1.setDate();
		// Expected
		boolean result = service.createOrder(order1);
		// Assertion
		Assertions.assertTrue(result);
	}

	@Test
	public void createOrderCatchTest() throws OrderNotCreatedException {
		Throwable exception = Assertions.assertThrows(OrderNotCreatedException.class, () -> {
			Mockito.doThrow(OrderNotCreatedException.class).when(service.createOrder(order1));

		});
	
		// Assert
		Assertions.assertEquals("The order cannot be created, check the request body", exception.getMessage());	
	}

	// The h2 console never has any entries and have been unable to find a way to resolve this issue
	// EDIT: I am now suspicious this quirk is due to the @MockBean annotation!
	
//	@Test
//	public void deleteOrderTest() throws IdNotFoundException, OrderNotCreatedException, OrdersNotFoundException {
//		// Act
//		repo.save(orderEnt1);
//		service.createOrder(order1);
//		System.out.println(service.getAllOrders());
//		// Arrange
//		boolean result = service.deleteOrder(1l);
//		// Assert
//		Assertions.assertTrue(result);
//		// Verify
//	}
	
	@Test
	public void deleteOrderCatchTest() throws IdNotFoundException {
		// Act
		Throwable exception = Assertions.assertThrows(IdNotFoundException.class, () -> {
			Mockito.doThrow(IdNotFoundException.class).when(service.deleteOrder(1l));
		});
		// Assert
		Assertions.assertEquals("Cannot find the specified Id", exception.getMessage());
	}
	
	@Test
	public void updateOrderTest() throws IdNotFoundException {
		// Act 
		Mockito.when(repo.findById(1l)).thenReturn(Optional.of(orderEnt1));
		Order result = service.updateOrder(1l, orderId1);
		// Assert
		Assertions.assertEquals(orderId1, result);
		Mockito.verify(repo, Mockito.times(1)).findById(1l);
	}
	
	@Test
	public void updateOrderCatchTest() throws IdNotFoundException {
		Throwable exception = Assertions.assertThrows(IdNotFoundException.class, () -> {
			Mockito.doThrow(IdNotFoundException.class).when(service.updateOrder(1l, orderId1));

		});
	
		// Assert
		Assertions.assertEquals("Cannot find the specified Id", exception.getMessage());
	}
	
}