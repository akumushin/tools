package com.manager.web.customers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manager.web.customers.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Override
	@Query("SELECT c from Customer c JOIN FETCH c.user WHERE c.id = :id")
	Optional<Customer> findById(Long id);

}
