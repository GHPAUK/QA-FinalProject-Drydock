package com.qa.ordermngt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.ordermngt.entitydto.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

}
