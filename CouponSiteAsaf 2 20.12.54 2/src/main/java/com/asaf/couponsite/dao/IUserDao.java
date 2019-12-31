package com.asaf.couponsite.dao;


import com.asaf.couponsite.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserDao extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.userName= :userName AND u.password= :password")

	User findUserByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.userName = :userName")

	boolean existsByName(@Param("userName") String userName);

	@Query("SELECT u FROM User u WHERE u.id=:userId")
	User findByUserId(@Param("userId") Long userId);

	@Query("SELECT u FROM User u WHERE u.password=:password")
	List<User> findByPassword(@Param("password") String password);

	@Query("SELECT u FROM User u WHERE u.userName=:userName AND u.password=:password")
	List<User> findUsersByNameAndPassword(@Param("userName") String userName, @Param("password") String password);

	@Query("SELECT u FROM User u WHERE u.userName=:userName")
	User findByUserName(@Param("userName") String userName);
}
