package com.asaf.couponsite.logic;


import com.asaf.couponsite.dao.ICompanyDao;
import com.asaf.couponsite.entities.Company;
import com.asaf.couponsite.entities.Coupon;
import com.asaf.couponsite.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    private ICompanyDao companyDao;

    public void createCompany(Company company) throws Exception {
        List<User> users = company.getUsers();
        for (User user : users) {
            user.setCompany(company);
        }
        List<Coupon> coupons = company.getCoupons();
        for (Coupon coupon :coupons) {
        	coupon.setCompany(company);
		}
        companyDao.save(company);
    }

    public void deleteCompany(long id) throws Exception {
        Company company = getCompany(id);
        companyDao.delete(company);
    }

    public Company getCompany(long id) throws Exception{
        Company company = companyDao.findByCompanyId(id);
        if(company == null){
			throw new Exception("Company does not exist");
		}
        return company;
    }

    public List<Company> getCompanyByName(String name) throws Exception{
		List<Company> companies = companyDao.getByName(name);
		if(companies.size() == 0){
			throw new Exception("Company does not exist");
		}
		return companies;
    }

    public List<Company> getAllCompanies() throws Exception {
        List<Company> companies = (List<Company>) companyDao.findAll();
        return companies;
    }

    public void deleteAllCompanies() throws Exception {
        companyDao.deleteAll();
    }

}

