package com.github.andrenaves7.order.model.repository;

import com.github.andrenaves7.order.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
