package com.asaf.couponsite.api;


import com.asaf.couponsite.entities.Company;
import com.asaf.couponsite.entities.RegisterCompany;
import com.asaf.couponsite.exceptions.ApplicationException;
import com.asaf.couponsite.logic.CompanyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyApi {

    @Autowired
    private CompanyController companyController;

    @PostMapping
    public void createCompany(@RequestBody RegisterCompany registerCompany) throws Exception {
        companyController.createCompany(registerCompany);
    }

    @DeleteMapping("/{companyId}")
    public void deleteCompany(@PathVariable("companyId") long id) throws Exception {
        companyController.deleteCompany(id);
    }

    @GetMapping
    public List<Company> getAllCompanies() throws Exception {
        return companyController.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable("id") long id) throws Exception {
        return companyController.getCompany(id);
    }

    @GetMapping("/byName")
    public Company getCompanyByCompanyName(@RequestParam("name") String name) throws Exception {
        return companyController.getCompanyByName(name);
    }

    @DeleteMapping
    public void deleteAllCompanies() throws Exception {
        companyController.deleteAllCompanies();
    }

}
