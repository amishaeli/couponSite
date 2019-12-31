package com.asaf.couponsite.dao;


import com.asaf.couponsite.entities.Purchase;
import org.springframework.data.repository.CrudRepository;


public interface IPurchaseDao extends CrudRepository<Purchase, Long> {

}
