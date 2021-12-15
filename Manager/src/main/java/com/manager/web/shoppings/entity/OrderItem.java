package com.manager.web.shoppings.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.manager.web.accounts.entity.SuperEntity;
import com.manager.web.companys.entity.Product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="order_item")
public class OrderItem  extends SuperEntity  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@ManyToOne
	@JoinColumn(name="order_id", nullable = false)
	private Orders orders;
	@ManyToOne
	@JoinColumn(name="product_id", nullable = false)
	private Product product;
	@Column(nullable = false)
	private long price;
	@Column(nullable = false)
	private long discount;
	@Column(nullable = false)
	private int quantity;
	@Column(nullable = false)
	private float taxRate; 
}
