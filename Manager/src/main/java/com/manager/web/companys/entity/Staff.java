package com.manager.web.companys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="staff")
public class Staff extends SuperEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private long id;
	@Column(name="first_name", length = 50)
	private String firstName;
	@Column(name="middle_name", length = 50)
	private String middleName;
	@Column(name="last_name", length = 50)
	private String lastName;
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
}
