package com.github.andrenaves7.order.model.repository;

import com.github.andrenaves7.order.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
