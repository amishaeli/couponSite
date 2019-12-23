package com.asaf.couponsite.logic;


import com.asaf.couponsite.dao.ICompanyDao;
import com.asaf.couponsite.dao.IUserDao;
import com.asaf.couponsite.entities.Company;
import com.asaf.couponsite.entities.Coupon;
import com.asaf.couponsite.entities.RegisterCompany;
import com.asaf.couponsite.entities.User;
import com.asaf.couponsite.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    private ICompanyDao companyDao;
    private IUserDao userDao;

    public void createCompany(RegisterCompany registerCompany) throws Exception {
        companyDao.save(registerCompany.getCompany());
        long companyId=companyDao.getByName(registerCompany.getCompany().getName()).getId();

        //consider to add delay between set to get company
        registerCompany.getUser().getCompany().setId(companyId);
        registerCompany.getUser().setUserType(UserType.COMPANY);

        User debug= registerCompany.getUser();
        userDao.save(registerCompany.getUser());

//        User user = registerCompany.getUser();

//        for (User user : users) {
//            user.setCompany(company);
//        }
//        List<Coupon> coupons = company.getCoupons();
//        for (Coupon coupon :coupons) {
//        	coupon.setCompany(company);
//		}
//        companyDao.save(company);
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

    public Company getCompanyByName(String name) throws Exception{
		Company companies = companyDao.getByName(name);
		if(companies == null){
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

