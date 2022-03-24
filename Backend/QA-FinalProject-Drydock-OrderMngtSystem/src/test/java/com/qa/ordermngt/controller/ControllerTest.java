package com.qa.ordermngt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
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
import com.qa.ordermngt.entity.OrderEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql", "classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dev")
public class ControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testCreateOrder() throws Exception {
		OrderEntity create = new OrderEntity("test", "test", 10, false, false, 10, 0, null);
		String orderJson = this.mapper.writeValueAsString(create);
		RequestBuilder req = post("/order").contentType(MediaType.APPLICATION_JSON).content(orderJson);
		OrderEntity saved = new OrderEntity(3l, "test", "test", 10, false, false, 10, 67.0f, Calendar.getInstance().getTime());
		String savedOrder = this.mapper.writeValueAsString(saved);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string("{\"Created\":" + savedOrder.toString() + "}");
		this.mvc.perform(req).andExpect(checkStatus);
	}

	@Test
	public void testDeleteOrder() throws Exception {
		Long id = 2l;
		RequestBuilder req = delete("/delete/" + id);
		OrderEntity deleted = new OrderEntity(2l, "TEST_CUSTOMER2", "TEST_VEHICLE2", 100, true, true, 50, 0, null);
		String deletedJson = this.mapper.writeValueAsString(deleted);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string("{\"Order Deleted\":" + deletedJson + "}");
		this.mvc.perform(req).andExpect(checkStatus).andExpectAll(checkBody);
	}
	
	@Test
	public void testUpdate() throws Exception {
		Long id = 2l;
		OrderEntity toUpdate = new OrderEntity("TEST_CUSTOMER2", "TEST_VEHICLE2", 100, true, true, 50, 0, null);
		String toUpdateJson = this.mapper.writeValueAsString(toUpdate);
		RequestBuilder req = put("/update/" + id).contentType(MediaType.APPLICATION_JSON).content(toUpdateJson);
		OrderEntity updated = new OrderEntity(2l, "TEST_CUSTOMER2", "TEST_VEHICLE2", 100, true, true, 50, 0, null);
		String updatedJson = this.mapper.writeValueAsString(updated);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string(updatedJson);
		this.mvc.perform(req).andExpect(checkStatus);
	}
	
	
	@Test
	public void testGetAllOrders() throws Exception {
		OrderEntity a = new OrderEntity(1l, "TEST_CUSTOMER1", "TEST_VEHICLE1", 100, true, true, 50, 0, null);
		OrderEntity b = new OrderEntity(2l, "TEST_CUSTOMER2", "TEST_VEHICLE2", 100, true, true, 50, 0, null);

		List<OrderEntity> db = List.of(a, b);
		
		String dbJson = this.mapper.writeValueAsString(db);
		
		RequestBuilder req = get("/getOrders");
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(dbJson);
		this.mvc.perform(req).andExpect(checkStatus);
	}
	
	@Test
	public void testGetOrderById() throws Exception {
		Long id = 1l;
		OrderEntity found = new OrderEntity(1l, "TEST_CUSTOMER1", "TEST_VEHICLE1", 100, true, true, 50, 0, null);
		String foundJson = this.mapper.writeValueAsString(found);
		
		RequestBuilder req = get("/getOrder/" + id);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(foundJson);
		
		this.mvc.perform(req).andExpect(checkStatus);
	}
	
}
