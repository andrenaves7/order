package com.github.andrenaves7.order.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.andrenaves7.order.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
