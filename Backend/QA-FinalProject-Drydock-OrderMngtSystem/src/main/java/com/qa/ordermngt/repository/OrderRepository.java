package com.qa.ordermngt.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.ordermngt.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	public List<OrderEntity> findAllByOrderByDateAsc();
	
	public List<OrderEntity> findOrdersByDate(Date orderDate);
	
	public List<OrderEntity> findAllByOrderByCostDesc();

}
