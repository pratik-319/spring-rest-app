package com.oracle.dao;

import java.util.List;

import com.oracle.model.Customer;

public interface CustomerDao {
	public void createCustomer(Customer cust);
	public Customer readCustomerByEmail(String email);
	public List<Customer> readAllCustomer();
	public void deleteCustomer(String email);
	void updateCustomer(Customer cust);
}
