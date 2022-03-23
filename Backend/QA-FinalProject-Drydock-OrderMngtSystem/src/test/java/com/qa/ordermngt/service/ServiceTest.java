package com.qa.ordermngt.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.ordermngt.entity.OrderEntity;
import com.qa.ordermngt.model.Order;
import com.qa.ordermngt.repository.OrderRepository;
import com.qa.ordermngt.utils.IdNotFoundException;
import com.qa.ordermngt.utils.OrderNotCreatedException;
import com.qa.ordermngt.utils.OrdersNotFoundException;

@SpringBootTest
@ActiveProfiles("dev")
public class ServiceTest {

	@MockBean
	private OrderRepository repo;

	@Autowired
	private OrderService service;

	private OrderEntity inputEnt;
	private OrderEntity returnedEnt;
	private Order input;
	private Order returned;

	@BeforeEach
	void setup() {
		inputEnt = new OrderEntity("test", "test", 10, true, true, 10);
		returnedEnt = new OrderEntity(1l, "test", "test", 10, false, false, 10, 67.0f,
				Calendar.getInstance().getTime());
		input = new Order("test", "test", 10, true, true, 10);
		returned = new Order(1l, "test", "test", 10, false, false, 10, 67.0f, Calendar.getInstance().getTime());
	}

	@Test
	public void createOrderTest() throws OrderNotCreatedException {
		// When
		Mockito.when(this.repo.save(inputEnt)).thenReturn(returnedEnt);
		// Then
		assertThat(this.service.createOrder(returned)).usingRecursiveComparison().ignoringFields("date")
				.isEqualTo(returnedEnt);
		// Verify
//		Mockito.verify(this.repo, Mockito.times(1)).save(returnedEnt);
	}

	@Test
	public void createOrderCatchTest() throws OrderNotCreatedException {
		Throwable exception = Assertions.assertThrows(OrderNotCreatedException.class, () -> {
			Mockito.doThrow(OrderNotCreatedException.class).when(service.createOrder(input));

		});

		// Assert
		Assertions.assertEquals("The order cannot be created, check the request body", exception.getMessage());
	}

	@Test
	public void deleteOrderTest() throws IdNotFoundException, OrderNotCreatedException, OrdersNotFoundException {
		// Given
		Long id = 1l;
		Optional<OrderEntity> opt = Optional.of(returnedEnt);
		// When
		Mockito.when(this.repo.findById(id)).thenReturn(opt);
		// Then
		assertThat(this.service.deleteOrder(id)).isEqualTo(returnedEnt);
		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
	}

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
		// Given
		Long id = 1l;
		Order toUpdate = new Order("test", "test", 10, false, false, 10);
		Optional<OrderEntity> opt = Optional.of(returnedEnt);
		OrderEntity updated = new OrderEntity(id, toUpdate.getCustomer(), toUpdate.getVehicleType(),
				toUpdate.getDisplacement(), toUpdate.isMilitary(), toUpdate.isWeaponised(),
				toUpdate.getResourcesRequired(), toUpdate.getCost(), toUpdate.getDate());
		// When
		Mockito.when(this.repo.findById(id)).thenReturn(opt);
		Mockito.when(this.repo.save(updated)).thenReturn(updated);
		// Then
		assertThat(this.service.updateOrder(id, toUpdate)).usingRecursiveComparison().ignoringFields("date", "cost")
				.isEqualTo(updated);
		// Verify
		Mockito.verify(this.repo, Mockito.times(2)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(returnedEnt);

	}

	@Test
	public void updateOrderCatchTest() throws IdNotFoundException {
		Throwable exception = Assertions.assertThrows(IdNotFoundException.class, () -> {
			Mockito.doThrow(IdNotFoundException.class).when(service.updateOrder(1l, input));

		});

		// Assert
		Assertions.assertEquals("Cannot find the specified Id", exception.getMessage());
	}

	@Test
	public void getAllOrdersTest() throws OrdersNotFoundException {
		// Given
		List<OrderEntity> allOrders = new ArrayList<>();
		allOrders.add(inputEnt);
		// When
		Mockito.when(this.repo.findAll()).thenReturn(allOrders);
		// Then
		assertThat(this.service.getAllOrders()).usingRecursiveComparison().ignoringFields("cost").isEqualTo(allOrders);
		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	public void getAllOrdersCatchTest() {
		Throwable exception = Assertions.assertThrows(OrdersNotFoundException.class, () -> {
			Mockito.doThrow(OrdersNotFoundException.class).when(service.getAllOrders());

		});

		// Assert
		Assertions.assertEquals("No orders found in database", exception.getMessage());
	}

	@Test
	public void getOrderByIdTest() throws IdNotFoundException {
		// Given
		Long id = 1l;
		Optional<OrderEntity> orderOpt = Optional.of(returnedEnt);
		// When
		Mockito.when(this.repo.findById(id)).thenReturn(orderOpt);
		// Then
		assertThat(this.service.getOrderById(id)).usingRecursiveComparison().ignoringFields("cost").isEqualTo(returnedEnt);
		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	public void getOrderByIdCatchTest() {
		Throwable exception = Assertions.assertThrows(IdNotFoundException.class, () -> {
			Mockito.doThrow(IdNotFoundException.class).when(service.getOrderById(1l));

		});

		// Assert
		Assertions.assertEquals("Cannot find the specified Id", exception.getMessage());
	}
}