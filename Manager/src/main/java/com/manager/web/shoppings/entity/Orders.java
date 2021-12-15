package com.manager.web.shoppings.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.manager.web.accounts.entity.SuperEntity;
import com.manager.web.customers.entity.Customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="orders")
public class Orders extends SuperEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	@Column(length = 1000)
	private String address;
	@Column(nullable = false)
	private long total;
	@Column(nullable = false)
	private boolean shipped;
	@Column(length = 1000)
	private String remark;
	@OneToMany(mappedBy = "orders")
	private List<OrderItem> orderItems;
	
	
}
