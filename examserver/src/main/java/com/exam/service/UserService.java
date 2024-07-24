package com.exam.service;

import java.util.Set;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {
	
	//create user
	public User createUser(User user,Set<UserRole> userRoles) throws UsernameNotFoundException;
	//get by usename;
	public User getByUserName(String username);
	//delete user id
	public void deleteUser(Long userId);

}
