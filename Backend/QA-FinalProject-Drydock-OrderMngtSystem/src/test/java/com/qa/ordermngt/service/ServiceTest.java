package com.qa.ordermngt.service;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.ordermngt.repository.OrderRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Sql(scripts = {"classpath:init_test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dev")
public class ServiceTest {
	
	@MockBean
	private OrderRepository repo;
	
	@Autowired
	private OrderService service;
	
	

}
