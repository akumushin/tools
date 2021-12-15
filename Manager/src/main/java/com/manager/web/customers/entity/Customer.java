package com.manager.web.customers.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.manager.web.accounts.entity.UserInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "customer")
@EqualsAndHashCode(callSuper = false)
public class Customer implements Serializable {
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L; // v
	@Id
	@Column(name="id")
	private Long id;
	@MapKey
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="id", referencedColumnName = "id")
	@Fetch(FetchMode.JOIN)
	private UserInfo user;
	private long balance;
	@Column(length = 300)
	private String address;
	@Column(name="phone",length = 15)
	private String phone;
	@Column(name="first_name",length = 50)
	private String firstName;
	@Column(name="middle_name",length = 50)
	private String middleName;
	@Column(name="last_name",length = 50)
	private String lastName;
	private LocalDate birthday;
}
