package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner{
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try {
		//System.out.println("Starting code");
//		
		User user=new  User();
		user.setFirstName("Santosh");
		user.setLastName("Honrao");
		user.setUsername("Santosh123");
		user.setPassword("Santosh123");
		user.setEmail("abc@123");
		user.setProfile("defaut.png");
		user.setPhone("774747474747");
		
		Role  role1=new Role();
		role1.setRoleId(44L);
		role1.setRoleName("ADMIN");
		
		
		Set<UserRole>userRoles=new HashSet<UserRole>();
		UserRole userRole=new UserRole();
		userRole.setRole(role1);
		userRole.setUser(user);
		
		userRoles.add(userRole);
		
	User user1=	this.userService.createUser(user, userRoles);
	System.out.println(user1);
		}
		catch (UsernameNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
