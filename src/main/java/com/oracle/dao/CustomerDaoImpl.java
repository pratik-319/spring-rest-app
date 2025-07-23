package com.oracle.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oracle.model.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void createCustomer(Customer cust) {
		entityManager.persist(cust); // this persist will insert a new record

	}

	@Override
	public Customer readCustomerByEmail(String email) {

		return entityManager.find(Customer.class, email); // will find and select the record
	}

	@Override
	public List<Customer> readAllCustomer() {

		String jpql = "SELECT c FROM Customer c"; // * alias
		return entityManager.createQuery(jpql, Customer.class).getResultList();
	}

	@Override
	@Transactional
	public void deleteCustomer(String email) {
		// Find the customer by email
		Customer customer = entityManager.find(Customer.class, email);

		if (customer != null) {
			// Remove the customer from the database
			entityManager.remove(customer);
		} else {
			throw new RuntimeException("Customer not found with email: " + email);
		}
	}

	@Override
	@Transactional
	public void updateCustomer(Customer cust) {
		// Find the existing customer by email
		Customer existingCustomer = entityManager.find(Customer.class, cust.getEmail());

		if (existingCustomer != null) {
			// Update the customer details
			existingCustomer.setFirstName(cust.getFirstName());
			existingCustomer.setLastName(cust.getLastName());
			entityManager.merge(existingCustomer); // Merges the updated object
		} else {
			throw new RuntimeException("Customer not found with email: " + cust.getEmail());
		}
	}

}
