package com.asaf.couponsite.logic;


import com.asaf.couponsite.cache.ICacheController;
import com.asaf.couponsite.dao.IUserDao;
import com.asaf.couponsite.data.LoggedInUserData;
import com.asaf.couponsite.data.LoginResponseDataObject;
import com.asaf.couponsite.entities.Company;
import com.asaf.couponsite.entities.User;
import com.asaf.couponsite.enums.ErrorType;
import com.asaf.couponsite.enums.UserType;
import com.asaf.couponsite.exceptions.ApplicationException;
import com.asaf.couponsite.exceptions.UserNotFoundException;
import com.asaf.couponsite.utils.DateUtils;
import com.asaf.couponsite.utils.StringValidation;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Random;

@Controller
public class UserController {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private ICacheController cacheController;

//	@Autowired
//	@Value("${maxFailedLogin}")
//	private int maxOfFailedLogin;

//	@PostConstruct
//	public void postConstruct() {
//		System.out.println("This is the max number of login tries " + maxOfFailedLogin);
//	}

    private int generateToken(String userName, LoggedInUserData loggedInUserData) {

        Random rnd = new Random();
        String salt = "#####";
        int token = (userName + rnd.nextInt(9999999) + salt + loggedInUserData.getUserId()).hashCode();

        return token;
    }

    public long createUser(User user) throws ApplicationException {

//		if (isUserExistByName(user.getUserName())) {
//			throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS, DateUtils.getCurrentDateAndTime()
//					+ "Error in userController.createUser(), User's name already exists.");
//		}

        validateUser(user);

        userDao.save(user);

        return user.getId();
    }

    public void updateUser(User user) throws ApplicationException {

        User originUser = getUserById(user.getId());
        String UserOriginUserName = originUser.getUserName();

        if (!user.getUserName().equalsIgnoreCase(UserOriginUserName)) {
            if (isUserExistByName(user.getUserName())) {
                throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS, DateUtils.getCurrentDateAndTime()
                        + "Error in userController.updateUser(), User's name already exists.");
            }
        }

        validateUser(user);

        userDao.save(user);
    }

    public User getUserById(long userId) throws ApplicationException {

        User user = userDao.findById(userId).get();
        return user;
    }

    public void deleteUser(long userId) throws ApplicationException {

        User user = userDao.findById(userId).get();
        userDao.delete(user);
    }

    public boolean isUserExistByName(String userName) throws ApplicationException {
        return userDao.existsByName(userName);
    }

    public List<User> getAllUsers() throws ApplicationException {

        List<User> users = (List<User>) userDao.findAll();
        return users;
    }

    private void validateUser(User user) throws ApplicationException {

        if (user == null) {
            throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE, DateUtils.getCurrentDateAndTime()
                    + "Error in userController.validateUser(), You must insert details");
        }

        if (user.getUserName() == null) {
            throw new ApplicationException(ErrorType.MUST_ENTER_NAME, DateUtils.getCurrentDateAndTime()
                    + "Error in companyController.validateCompny() " + user + "Null name, you must insert a name.");
        }

        if (user.getUserName().isEmpty()) {
            throw new ApplicationException(ErrorType.MUST_ENTER_NAME, DateUtils.getCurrentDateAndTime()
                    + "Error in companyController.validateCompny() " + user + "An empty name, you must insert a name.");
        }

        if (!StringValidation.isPasswordValid(user.getPassword())) {
            throw new ApplicationException(ErrorType.INVALID_PASSWORD, DateUtils.getCurrentDateAndTime()
                    + "Error in StringValidation.isPasswordValid(), Invalid password.");
        }
    }

    public LoginResponseDataObject login(String userName, String password) throws ApplicationException, UserNotFoundException {
        User user = getUserByUserNameAndPassword(userName, password);
//        if (user == null) {
//            throw new ApplicationException(ErrorType.LOGIN_FAILED, "Login failed. Please try again.");
//        }
        long userId = user.getId();
        LoggedInUserData loggedInUserData;
        Company company = user.getCompany();
        UserType userType = user.getUserType();

        switch (userType) {
            case ADMIN:
            case CUSTOMER:
                loggedInUserData = new LoggedInUserData(userType, userId);
                break;
            case COMPANY:
//				Company company = user.getCompany();
                loggedInUserData = new LoggedInUserData(userType, company.getId(), userId);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + userType);
        }


//		Long companyId = user.getCompany().getId();
//		Company company = user.getCompany();
//		long userId = user.getId();
//
//		LoggedInUserData loggedInUserData;
//
//		if(company !=null) {
//			loggedInUserData = new LoggedInUserData(userType, company.getId(), userId);
//		} else {
////			company.setId(0);
//			loggedInUserData = new LoggedInUserData(userType, userId);
//		}

//		if(companyId != null) {
//			loggedInUserData = new LoggedInUserData(userType, companyId, userId);
//		}
//		else {
//			loggedInUserData = new LoggedInUserData(userType, userId);
//		}

        int token = generateToken(userName, loggedInUserData);
//		int token = 123;
        loggedInUserData.setToken(token);

        // Converting the int into a String
        String strToken = String.valueOf(token);
        // Save login user data in cache
        cacheController.put(strToken, loggedInUserData);
        LoginResponseDataObject loginResponseDataObject;
        if (userType != UserType.COMPANY) {
            loginResponseDataObject = new LoginResponseDataObject(token, loggedInUserData.getUserType(), userId, 0);
        } else {
            loginResponseDataObject = new LoginResponseDataObject(token, loggedInUserData.getUserType(), userId, company.getId());
        }
        return loginResponseDataObject;
    }


//	public LoginResponseDataObject login(String userName, String password) throws HibernateException, UserNotFoundException {
//		User user  = getUserByUserNameAndPassword(userName, password);
//		UserType  userType = user.getUserType();
//		int token = user.hashCode();
//		LoginResponseDataObject loginResponseDataObject = new LoginResponseDataObject(userType, token, user.getId(), user.getCompany() == null ? -1 : user.getCompany().getId());
//		cacheController.put(loginResponseDataObject.getUserId(),loginResponseDataObject.getToken());
//
//		return loginResponseDataObject;
//	}

    public User getUserByUserNameAndPassword(String userName, String password) throws
            HibernateException, UserNotFoundException {
        validateUserName(userName);
        validatePassword(password);
        List<User> users = userDao.findUsersByNameAndPassword(userName, password);
        if (users.size() == 0) {
            throw new UserNotFoundException();
        }
        return users.get(0);
    }

    public User getUserByUserName(String userName) throws ApplicationException {
        validateUserName(userName);
        User user = userDao.findByUserName(userName);
        if (user == null) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR, "UserName doesn't exist");
        }
        return user;
    }

    public User getUserByPassword(String password) throws ApplicationException {
        validatePassword(password);
        List<User> users = userDao.findByPassword(password);
        if (users.size() == 0) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR, "Password doesn't exist");
        }
        return users.get(0);
    }

    public void validateUserId(long id) throws ApplicationException {
        //i have to add a validation for id is typed as an integer number only:
        //		if  (id+0 != id) {
        //			throw new ApplicationException(ErrorType.GENERAL_ERROR, "UserId must be an integer number");
        //		}
        if (id == 0) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR, "UserId is null");
        }
        if (id < 0) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR, "UserId must be positive");
        }
    }

    public void validatePassword(String password) throws ApplicationException {
        if (password == null) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR, "A null password");
        }
        if (password.isEmpty()) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR, "An empty password");
        }
    }

    public void validateUserName(String userName) throws ApplicationException {
        if (userName == null) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR, "A null userName");
        }
        if (userName.isEmpty()) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR, "An empty userName");
        }
    }

}

