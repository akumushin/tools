package com.manager.web.companys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.manager.web.accounts.entity.SuperEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="product_campaign")
public class ProductCampaign extends SuperEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;
	@Column(name= "types", nullable = false)
	private String types;
	@Column(name="unit_quantity", nullable = false)
	private int unitQuantity;
	@Column(name="unit_amount", nullable = false)
	private long unitAmount;
	@Column(name="discount_amount", nullable = false)
	private long discountAmount;
	@Column(name="discount_percent", nullable = false)
	private float discountPercent;
	@Column(name="campaign_group", nullable = false)
	private int campaignGroup;
	@Column(nullable = false)
	private boolean enabled;
	
}
