package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/test")
	public String test()
	{
		return "Welcome to backend api of Examportal";
	}
	//Create User
	@PostMapping("/")
	public User createUser(@RequestBody User user)
	{User user1=null;
		//try {
		user.setProfile("default.png");
		Set<UserRole> roles=new HashSet<>();
		Role role=new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		UserRole userRole=new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		
			 user1= this.userService.createUser(user, roles);
//		}catch (UsernameNotFoundException e) {
//			e.printStackTrace();
//			//return null;
//		}
		return user1;
		
		
		
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable ("username") String username)
	{
		return this.userService.getByUserName(username);
		
	}

	@DeleteMapping("/{userid}")
	public void deleteUser (@PathVariable ("userid") Long userid)
	{
		userService.deleteUser(userid);
		
	}

}
