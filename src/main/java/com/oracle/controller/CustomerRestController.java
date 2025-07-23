package com.oracle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.exceptions.DuplicateEmailException;
import com.oracle.exceptions.NoSuchCustomerException;
import com.oracle.model.Customer;
import com.oracle.service.CustomerService;



@RestController
@RequestMapping(path = "customer-api")
public class CustomerRestController {
	
	@Autowired
	private CustomerService service;
	
	// http://localhost:8080/customer-api
	@PostMapping
	public void addCustomer(@RequestBody Customer cust) {
		service.addCustomer(cust);
	}
	
	@GetMapping("/{email}")
	public Customer getCustomerByEmail(@PathVariable String email) {
		return service.findCustomerByEmail(email);
	}
	
	@GetMapping("/all")
	public List<Customer> getAllCustomers(){
		return service.findAllCustomers();
	}
	
	@DeleteMapping("/{email}")
	public ResponseEntity<String> deleteCustomer(@PathVariable String email) {
	    try {
	        service.removeCustomer(email);
	        return ResponseEntity.ok("Customer deleted successfully");
	    } catch (Exception e) {
	        return ResponseEntity.status(404).body("Customer not found");
	    }
	}

	@PutMapping("/{email}")
    public ResponseEntity<String> updateCustomer(@PathVariable String email, @RequestBody Customer customer) {
        try {
            customer.setEmail(email);  
            service.changeCustomer(customer);
            return ResponseEntity.ok("Customer updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Customer not found");
        }
    }
	
	@ExceptionHandler(NoSuchCustomerException.class)
	public ResponseEntity<String> handleNoSuchException(NoSuchCustomerException ex){
		return ResponseEntity.status(500).body("--exception called--\n" + ex.getMessage());
	}
	
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<String> handleDuplicateException(DuplicateEmailException ex){
		return ResponseEntity.status(500).body("--exception called --\n" + ex.getMessage());
	}
}
