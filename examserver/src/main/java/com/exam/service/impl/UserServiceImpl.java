package com.exam.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.repo.UserRoleRepository;
import com.exam.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws UsernameNotFoundException {
		System.out.println(user.getUsername());

	User local 	=this.userRepository.findByUsername(user.getUsername());
	//System.out.println(local.toString());
	if(local!=null)
	{
		System.out.println("User is already there !!");
		//throw new Exception("User already present!!");
		throw new UsernameNotFoundException("User already prersents");
		
	}
	else
	{
		//user create 
		for(UserRole ur:userRoles)
		{
			roleRepository.save(ur.getRole());
		}
		user.getUserRoles().addAll(userRoles);
		local=this.userRepository.save(user);
		
	}
		//System.out.println(local.getId());
		return local;
	}

	
	//Getting user by username
	@Override
	public User getByUserName(String username) {
		// TODO Auto-generated method stub
		User  user= this.userRepository.findByUsername(username);
		//System.out.println("145267"+user.toString());
		
//		if(user!=null)
//		{
//		UserRole userRole=	userRoleRepository.findByUserRoleId(user.getId());
//	//	System.out.println(userRole.toString());
//		
//		if(userRole!=null)
//		{
//			System.out.println("In UserRole");
//			List<Role>roles=new ArrayList<>();
//			roles=roleRepository.findByRoleId(userRole.getUserRoleId());
//			
//			if(roles!=null) {
//				System.out.println("In roles");
//				
//				
//			}
//		//	System.out.println(userRole.toString());
//			
//		}
//		}
		return user;
	}


	//delete user
	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
		
	}

}
