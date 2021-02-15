package com.github.andrenaves7.order.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.andrenaves7.order.model.entity.Order;
import com.github.andrenaves7.order.model.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderResource {
	
	@Autowired
	private final OrderRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Order save(@RequestBody Order order) {
		return this.repository.save(order);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody Order updatedOrder) {
		this.repository.findById(id).map(order -> {
			updatedOrder.setId(id);
			return this.repository.save(updatedOrder);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.repository.deleteById(id);
	}

	@GetMapping
	public List<Order> list() {
		return this.repository.findAll();
	}

	@GetMapping("{id}")
	public Order listById(@PathVariable Long id) {
		return this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
