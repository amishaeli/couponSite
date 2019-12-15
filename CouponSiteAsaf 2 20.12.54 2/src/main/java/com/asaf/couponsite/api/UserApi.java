package com.asaf.couponsite.api;


import com.asaf.couponsite.data.LoginResponseDataObject;
import com.asaf.couponsite.data.UserLoginDetailsDataObject;
import com.asaf.couponsite.entities.User;
import com.asaf.couponsite.exceptions.ApplicationException;
import com.asaf.couponsite.logic.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserApi {

	@Autowired
	private UserController userController;

//	http://localhost:8080/user/login
	@PostMapping("/login")
	public LoginResponseDataObject login(@RequestBody UserLoginDetailsDataObject userLoginDetails) throws ApplicationException {
		System.out.println("userLoginDetails: " + userLoginDetails);
		return userController.login(userLoginDetails.getUserName(), userLoginDetails.getPassword());
	}
/*http://localhost:8080/user
CREATE USER ONLY WITHOUT COMPANYID
	{
      "userName":"Admin2",
      "password":"AAAAAAA1",
      "userType":"ADMIN"
}
 */
	@PostMapping
	public long createUser(@RequestBody User user) throws ApplicationException{
		return userController.createUser(user);
	}


	@GetMapping("{id}")
	public User getUserById(@PathVariable("id") long id) throws ApplicationException{
		return userController.getUserById(id);
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") long userId) throws ApplicationException{
		userController.deleteUser(userId);
	}

	@GetMapping
	public List<User> getAllUsers() throws ApplicationException{
		return userController.getAllUsers();
	}
}