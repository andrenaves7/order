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

import com.github.andrenaves7.order.model.entity.Product;
import com.github.andrenaves7.order.model.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductResource {

	@Autowired
	private final ProductRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product save(@RequestBody Product item) {
		return this.repository.save(item);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody Product updatedItem) {
		this.repository.findById(id).map(item -> {
			updatedItem.setId(id);
			return this.repository.save(updatedItem);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.repository.deleteById(id);
	}

	@GetMapping
	public List<Product> list() {
		return this.repository.findAll();
	}

	@GetMapping("{id}")
	public Product listById(@PathVariable Long id) {
		return this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
