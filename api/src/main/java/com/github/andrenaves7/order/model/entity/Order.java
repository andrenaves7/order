package com.github.andrenaves7.order.model.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.andrenaves7.order.model.enums.OrderStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "od_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDate createdAt;
	
	@Column(nullable = false)
	private OrderStatus orderStatus;
	
	@Column(nullable = false)
	@OneToMany
	@JoinColumn(name="order_id")
	private List<Item> itens;
	
	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDate.now();
	}
}
