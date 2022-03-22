package com.qa.ordermngt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.ordermngt.model.Order;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = { "classpath:init_test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dev")
public class ControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	// Test Objects
	Order testOrder = new Order("TestCustomer", "TestVehicle", 1000, true, true, 500);
	Order testOrderId = new Order(1l, "TEST_CUSTOMER1", "TEST_VEHICLE1", 100, true, true, 50, 5000, null);

	@Test
	public void testCreateOrder() throws Exception {
		// Arrange
		String listingJson = mapper.writeValueAsString(testOrder);
		RequestBuilder req = post("/order").contentType(MediaType.APPLICATION_JSON).content(listingJson);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string("{\"Created\":true}");

		// Act
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	public void testDeleteOrder() throws Exception {
		// Arrange
		Order orderToDelete = testOrder;
		orderToDelete.setId(1l);
		RequestBuilder req = delete("/delete/1");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string("{\"Order Deleted\":true}");
		
		// Act
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testUpdate() throws Exception {
		// Arrange
		String listingJson = mapper.writeValueAsString(testOrderId);
		RequestBuilder req = put("/update/1").contentType(MediaType.APPLICATION_JSON).content(listingJson);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string("{\"id\":1,\"customer\":\"TEST_CUSTOMER1\",\"vehicleType\":\"TEST_VEHICLE1\",\"displacement\":100,\"military\":true,\"weaponised\":true,\"resourcesRequired\":50,\"cost\":0.0,\"date\":null}");
		
		// Act
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testGetAllOrders() throws Exception {
		// Arrange
		RequestBuilder req = get("/getOrders");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string(
				"[{\"id\":1,\"customer\":\"TEST_CUSTOMER1\",\"vehicleType\":\"TEST_VEHICLE1\",\"displacement\":100,\"military\":true,\"weaponised\":true,\"resourcesRequired\":50,\"cost\":3333.0,\"date\":null},{\"id\":2,\"customer\":\"TEST_CUSTOMER2\",\"vehicleType\":\"TEST_VEHICLE2\",\"displacement\":100,\"military\":true,\"weaponised\":true,\"resourcesRequired\":50,\"cost\":3333.0,\"date\":null}]");

		// Act
		mvc.perform(req).andExpect(checkStatus).andExpectAll(checkBody);
	}
	
	@Test
	public void testGetOrderById() throws Exception {
		// Arrange
		RequestBuilder req = get("/getOrder/1");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string("{\"id\":1,\"customer\":\"TEST_CUSTOMER1\",\"vehicleType\":\"TEST_VEHICLE1\",\"displacement\":100,\"military\":true,\"weaponised\":true,\"resourcesRequired\":50,\"cost\":0.0,\"date\":null}");
		
		// Act
		mvc.perform(req).andExpect(checkStatus).andExpectAll(checkBody);
	}
	
}
