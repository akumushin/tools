package com.manager.web.customers.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.web.accounts.repository.UserInfoRepository;
import com.manager.web.customers.entity.Customer;
import com.manager.web.customers.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired CustomerRepository customerRep;
	@Autowired UserInfoRepository userRep;
	@Transactional
	public void save(Customer customer) {
		userRep.save(customer.getUser());
		customer.setId(customer.getUser().getId());
		customerRep.save(customer);
	}
	@Transactional
	public void update(Customer customer) {
		customerRep.save(customer);
	}
	public Customer findByUserId(Long userId) {
		return customerRep.findById(userId).orElse(null);
	}
	@Transactional
	public void delete(Long userId) {
		customerRep.deleteById(userId);
	}
}
