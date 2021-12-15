package com.manager.web.companys.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.manager.web.accounts.entity.SuperEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="product")
public class Product extends SuperEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@Column(name="name",length = 255)
	private String name;
	@Column(name="display_name",length = 255)
	private String displayName;
	@Column(length = 1000)
	private String introduction;
	@Column(name="company_id")
	private Company company;
	@Column(nullable = false)
	private long price;
	@Column(name="tax_rate")
	private float taxRate;
	
	@Column(name="publish_time")
	private LocalDateTime publishTime;
	@Column(name="start_time")
	private LocalDateTime startTime;
	@Column(name="end_time")
	private LocalDateTime endTime;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "product")
	private List<ProductCampaign> productCampaigns;
	
}
