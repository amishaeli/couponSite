package com.asaf.couponsite.logic;


import com.asaf.couponsite.dao.ICustomerDao;
import com.asaf.couponsite.dao.IUserDao;
import com.asaf.couponsite.entities.Customer;
import com.asaf.couponsite.entities.Registration;
import com.asaf.couponsite.entities.User;
import com.asaf.couponsite.enums.ErrorType;
import com.asaf.couponsite.exceptions.ApplicationException;
import com.asaf.couponsite.utils.DateUtils;
import com.asaf.couponsite.utils.StringValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerController {

	@Autowired
	private ICustomerDao customerDao;

	@Autowired
	private IUserDao userDao;


	@Autowired
	private UserController userController;

//	public void createCustomer(Customer customer) throws ApplicationException {
//
//		validateCustomer(customer);
//
//		User user = customer.getUser();
//		userController.createUser(user);
//		customerDao.save(customer);
//	}

//	public void createCustomer(Customer customer) throws  Exception {
//		List<User> users = customer.getUsers();
//		for (User user: users) {
//			user.setCustomer(customer);
//		}
//		customerDao.save(customer);
//	}

	public void registerCustomer(Registration registration) throws  Exception {
		User user = registration.getUser();

		userDao.save(user);
		customerDao.save(registration.getCustomer());
		//userDao.save(registration.getUsers());
	}

	public void updateCustomer(Customer customer) throws ApplicationException {

		validateCustomer(customer);

		customerDao.save(customer);
	}

	public Customer getCustomerById(long customerId) throws Exception {

		Customer customer = customerDao.findByCustomerId(customerId);
		if(customer == null) {
			throw new Exception("Customer does not exist");
		}
		return customer;
	}

	public void deleteCustomer(long customerId) throws Exception {

		Customer customer = getCustomerById(customerId);
		if (customer == null) {
			throw new Exception("Customer does not exist");
		}
		customerDao.delete(customer);
	}

	public List<Customer> getAllCustomers() throws ApplicationException {

		List<Customer> customers =  (List<Customer>) customerDao.findAll();
		return customers;
	}

	private void validateCustomer(Customer customer) throws ApplicationException {

		if (customer.getFirstName() == null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE, DateUtils.getCurrentDateAndTime()
					+ "Error in customerController.validateCustomer(), You must insert details");
		}

		if (customer.getFirstName().isEmpty()) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME, DateUtils.getCurrentDateAndTime()
					+ "Error in customerController.validateCustomer() " + customer + "An empty name.");
		}

		if (customer.getLastName() == null) {
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE, DateUtils.getCurrentDateAndTime()
					+ "Error in customerController.validateCustomer(), You must insert details");
		}

		if (customer.getLastName().isEmpty()) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME, DateUtils.getCurrentDateAndTime()
					+ "Error in customerController.validateCustomer() " + customer + "An empty name.");
		}

		if (customer.getAddress() == null) {
			throw new ApplicationException(ErrorType.MUST_ENTER_ADDRESS, DateUtils.getCurrentDateAndTime()
					+ "Error in customerController.validateCustomer() " + customer + "Null name.");
		}

		if (customer.getAddress().isEmpty()) {
			throw new ApplicationException(ErrorType.MUST_ENTER_ADDRESS, DateUtils.getCurrentDateAndTime()
					+ "Error in customerController.validateCustomer() " + customer + "The company address is empty.");
		}

		if(!StringValidation.isEmailAddressValid(customer.getEmail())) {
			throw new ApplicationException(ErrorType.INVALID_EMAIL, DateUtils.getCurrentDateAndTime()
					+ "Error in StringValidation.isEmailAddressValid(), InValid Email.");
		}
	}

}


