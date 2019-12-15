package com.asaf.couponsite.dao;


import com.asaf.couponsite.entities.Customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerDao extends CrudRepository<Customer, Long> {


    List<Customer> getByFirstName(@Param("firstName") String name);

    List<Customer> getByLastName(@Param("lastName") String name);

    @Query("SELECT c FROM Customer c where c.id=:id")
    Customer findByCustomerId(@Param("id") Long id);
}
