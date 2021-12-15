package com.manager.web.customers.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.manager.web.customers.entity.Customer;
import lombok.Data;

@Data
public class CustomerForm{
	private Long id;
	private String address;
	private String firstName;
	private String middleName;
	private String lastName;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthday;
	private String phone;
	public Customer toCustomer() {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setAddress(address);
		customer.setBalance(0);
		customer.setBirthday(birthday);
		customer.setFirstName(firstName);
		customer.setMiddleName(middleName);
		customer.setLastName(lastName);
		customer.setPhone(phone);
		return customer;
	}
	public static CustomerForm from(Customer customer) {
		CustomerForm form = new CustomerForm();
		form.setAddress(customer.getAddress());
		form.setBirthday(customer.getBirthday());
		form.setFirstName(customer.getFirstName());
		form.setMiddleName(customer.getMiddleName());
		form.setLastName(customer.getLastName());
		form.setId(customer.getId());
		form.setPhone(customer.getPhone());
		return form;
	}
}
