package com.asaf.couponsite.dao;


import com.asaf.couponsite.entities.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICompanyDao extends CrudRepository<Company, Long> {

    @Query("SELECT c FROM Company c where c.name=:name")
    Company getByName(@Param("name") String name);

    @Query("SELECT c FROM Company c where c.id=:id")
    Company findByCompanyId(@Param("id") Long id);


}
