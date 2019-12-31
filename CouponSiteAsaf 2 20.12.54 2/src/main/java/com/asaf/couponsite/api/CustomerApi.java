package com.asaf.couponsite.api;


import com.asaf.couponsite.entities.Customer;
import com.asaf.couponsite.entities.Registration;
import com.asaf.couponsite.exceptions.ApplicationException;
import com.asaf.couponsite.logic.CustomerController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerApi {
	@Autowired
	private CustomerController customerController;

	@PostMapping("/register")
	public void registerCustomer(@RequestBody Registration registration) throws Exception {
		customerController.registerCustomer(registration);
	}

	@PutMapping
	public void updateCustomer(@RequestBody Customer customer) throws ApplicationException{
		customerController.updateCustomer(customer);
	}

	@GetMapping("/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") long customerId) throws Exception {
		return customerController.getCustomerById(customerId);
	}

	@DeleteMapping
	@RequestMapping("/{customerId}")
	public void deleteCustomer (@PathVariable("customerId") long customerId) throws Exception {
		customerController.deleteCustomer(customerId);
	}

	@GetMapping
	public List<Customer> getAllCustomers() throws ApplicationException{
		return customerController.getAllCustomers();
	}
}
