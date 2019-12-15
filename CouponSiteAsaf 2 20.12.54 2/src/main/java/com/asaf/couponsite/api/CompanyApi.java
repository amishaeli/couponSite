package com.asaf.couponsite.api;


import com.asaf.couponsite.entities.Company;
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
// All functions below are working!!!!!!
	@Autowired
	private CompanyController companyController;
/*
http://localhost:8080/company
/* Create Company, Coupon and User via Company
{
  "users":[
    {"userName":"samsung",
     "password":"AAAAAAA1",
     "userType":"COMPANY"
    }
    ],
  "coupons":[
    {"couponType":"GADGETS",
     "title":"Mobile Device",
     "description":"a",
     "createDate":"2019-12-08",
     "expirationDate":"2019-12-31",
     "amount":5,
     "price":12.3,
     "image":"adsdd"
    }
    ],
  "name":"Samsung",
  "address":"c",
  "email":"samsung@samsung.com"
}
 */
	@PostMapping
	public void createCompany(@RequestBody Company company) throws Exception {
		companyController.createCompany(company);
	}

	//http://localhost:8080/company/1
	@DeleteMapping("/{companyId}")
	public void deleteCompany(@PathVariable("companyId") long id) throws Exception {
		companyController.deleteCompany(id);
	}

	//    http://localhost:8080/company
	@GetMapping
	public List<Company> getAllCompanies() throws Exception {
		return companyController.getAllCompanies();
	}

	//    http://localhost:8080/company/5
	@GetMapping("/{id}")
	public Company getCompanyById(@PathVariable("id") long id) throws Exception {
		return companyController.getCompany(id);
	}

	//    http://localhost:8080/company/byName?name=apple
	@GetMapping("/byName")
	public List<Company> getCompanyByName(@RequestParam("name") String name) throws Exception {
		return companyController.getCompanyByName(name);
	}

	//    http://localhost:8080/company
	// Delete all Companies from Company and the associated Users from User and Coupons from Coupon
	@DeleteMapping
	public void deleteAllCompanies() throws Exception {
		companyController.deleteAllCompanies();
	}

}
